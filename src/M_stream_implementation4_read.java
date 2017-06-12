import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.RandomAccessFile;

import java.nio.MappedByteBuffer;

import java.nio.channels.FileChannel;

public class M_stream_implementation4_read implements M_readStream
{


	//Stream as a random access file

		private RandomAccessFile inputFile;
		
		//Number of maximum bytes of the buffer = number of integer * 4
		private int maxBufferSize;

		//Map between file and memory

		private MappedByteBuffer map;


		//The index of stream in the file
		private int startMappingIndex;

		//Pre-store the next number
		private int nextNumber;
		private boolean mapMemory;
		

		
		
		private String path;		
		private String filename;
		private String ext;
		
		private int b;
		private FileChannel channel;

		public M_stream_implementation4_read (String path, String filename, String extension, int b) {
			this.filename=filename;
			this.path = path;
			this.ext=extension;
			 File d = new File(path);
			   // Create directory now.
			   d.mkdirs();
			 this.b=b;
		}


		public void open() throws  IOException{

			inputFile = new RandomAccessFile(path + filename + ext, "r");
			this.channel = inputFile.getChannel();

			mapMemory = false;

			startMappingIndex = 0;

			nextNumber = 0;
			maxBufferSize=(int) channel.size();
			maxBufferSize=b*4;
			//mapMemory(0);
			mapMemory();
			
		}

		

		/**

		 * Map a portion of file containing mapSize bytes or to the end of file 
		 * @throws IOException 
		 */

		public void mapMemory() throws IOException{
			long bytesToMap =  Math.min(channel.size() - startMappingIndex , maxBufferSize);
				if (startMappingIndex + 1 < channel.size()) {
					map = channel.map(FileChannel.MapMode.READ_ONLY, startMappingIndex, bytesToMap);
					startMappingIndex = (int) (startMappingIndex + bytesToMap);
				} 
					
					


  
		}
		
		

		public Integer read_next() throws IOException {

			Integer result=map.getInt();

			if(!map.hasRemaining() && channel.size()>startMappingIndex){
				System.out.println(channel.size());
				mapMemory();
			}
			return result;
		}

		
		public void close() throws IOException {
			channel.close();
			inputFile.close();


		}



		public boolean end_of_stream() throws IOException {
			if(channel.size()==startMappingIndex && !map.hasRemaining()){
				return true;
			}
			return false;

		}


		public boolean has_more_values() throws IOException {
			if(channel.size()>startMappingIndex || map.hasRemaining()){
				return true;
			}
			return false;
		}


		public long getSize() throws Exception {

			return channel.size();

		 }
		public int getMaxBufferSize() {

			return maxBufferSize;

		}

		public String return_filename() {
			return filename;
		}


		public String return_path() {
			return path;
		}
	
		
		
		public static void main(String[] args) throws Exception {
			/**
			System.out.println("Read:");
			M_stream_implementation2_read r = new M_stream_implementation2_read("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "sortedSublist_2",".data");
			r.open();
			while(r.has_more_values()){
				r.read_next();
			}
			r.close();
			**/
			M_stream_implementation4_read m = new M_stream_implementation4_read ("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "sortedSublist_7",".data",5);
			//writes 32 bits - 8 integers
			m.open();
			System.out.println("Buffer size in bits :"+m.getMaxBufferSize());
			while(m.end_of_stream()==false){
				System.out.println(m.read_next());
			}
			m.has_more_values();
			System.out.println(m.getSize());
			m.close();
			

		}
	
}