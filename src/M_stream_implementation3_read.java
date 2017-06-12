import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.LinkedList;

public class M_stream_implementation3_read  implements M_readStream{
	
	private FileInputStream is;
	private DataInputStream ds;
	
	private LinkedList<Integer> buffer;
	private int buffer_size;
	
	
	private String path;
	private String filename;
	private String ext;
	
	
	
	public M_stream_implementation3_read (String path, String filename, String extension, int b) {
		this.path = path;
		this.ext=extension;
		 File d = new File(path);
		   // Create directory now.
		   d.mkdirs();
		   this.filename=filename;
		   this.buffer_size=b;
		 this.buffer = new LinkedList<Integer>();
	}
	
	public void open() throws IOException{
		is = new FileInputStream( new File(path + filename + ext) );
		//	System.out.println(is);
			 ds = new DataInputStream(is);
			// System.out.println(ds);
		
			for(int i =0; i<buffer_size;i++){
				if(ds.available()>0){
					buffer.add(ds.readInt());
				}
			}
	
	}
	
	
	public Integer read_next() throws IOException {

		if (buffer.isEmpty()){
			for(int i =0; i<buffer_size;i++){
				if(ds.available()>0){
					buffer.add(ds.readInt());
				}
			}
		}

		return buffer.poll();
				
	}

	
	
	public void close() throws IOException{
		ds.close();
		is.close();
	
	}
	
	
	public boolean end_of_stream() throws IOException{
		if (ds.available()>0 || !buffer.isEmpty()){
			//System.out.println("true");
			return false;
		}
		
		else return true;
	}
	
	public boolean has_more_values() throws IOException{
		if (ds.available()>0 || !buffer.isEmpty()){
			//System.out.println("true");
			return true;
		}
		
		else return false;
	}
	
	public String return_filename(){
		return filename;
	}
	
	public String return_path(){
		return path;
	}
	
	public static void main(String[] args) throws IOException {
		M_stream_implementation3_read m = new M_stream_implementation3_read("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile_0",".data", 4);
		//writes 32 bits - 8 integers
		m.open();

		while(m.has_more_values()){
			System.out.println(m.read_next());

		}
		m.close();
		


	}
	
	

}
