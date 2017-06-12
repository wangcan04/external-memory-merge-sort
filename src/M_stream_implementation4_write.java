

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


public class M_stream_implementation4_write implements M_writeStream {
	
	//private FileOutputStream outputFile;
		private RandomAccessFile outputFile;
		private int maxBufferSize;
		private MappedByteBuffer map;
		private int startMappingIndex;
		
		//Size of all integer to be written
		private long streamSize;
		
		
		
		private String path;		
		private String filename;
		private String ext;
		
		private int b;
		private FileChannel channel;
		
		
		public M_stream_implementation4_write (String path, String filename, String extension, int b) {
			 this.path = path;
			 this.ext=extension;
			 File d = new File(path);
			   // Create directory now.
			   d.mkdirs();
			   this.filename=filename;
			this.b=b;
			
		}


		public void create() throws IOException {
			//outputFile = new FileOutputStream(filePath);
			outputFile = new RandomAccessFile(path + filename +ext, "rw");
			channel=outputFile.getChannel();
			startMappingIndex = 0;
			maxBufferSize=b*4;
			mapMemory();
		}
		


		private void mapMemory() throws IOException {
			map = outputFile.getChannel().map(FileChannel.MapMode.READ_WRITE, startMappingIndex, maxBufferSize);
			startMappingIndex = (int) (startMappingIndex + maxBufferSize);

		}
		


		public void write(Integer number) throws IOException {
			

			//If there is no space to write 32-bit integer => map the next portion of stream to memory
			if (!map.hasRemaining()) {
				mapMemory();
			}
			  map.putInt(number);

		
		}

		public void close() throws IOException  {
			
			channel.close();
			outputFile.close();
		}



		public long getsize() throws IOException{
			return channel.size();
		}

		public String return_filename() {
			return filename;
		}


		public String return_path() {
			return path;
		}
/**
	
		public static void main(String[] args) throws IOException {
			M_stream_implementation4_write m = new M_stream_implementation4_write ("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile_00",".data",5);
			m.create( );
			System.out.println(m.getsize());
			
			m.write((Integer)2);
			m.write((Integer)2);
			m.write((Integer)4);
			m.write((Integer)5);
			m.write((Integer)101001);
			m.write((Integer)2);
			m.write((Integer)2);
			m.write((Integer)4);
			m.write((Integer)5);
			m.write((Integer)101001);
		
			System.out.println("Read:");
			M_stream_implementation4_read r = new M_stream_implementation4_read("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile_00",".data",5);
			r.open();
			while(r.has_more_values()){
				System.out.println(r.read_next());
			}
			r.close();
		
	
		}		**/
		
}