import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class M_stream_implementation3_write implements M_writeStream{
	

	private OutputStream os;
	private DataOutputStream dos;

	private String path;
	private String ext;
	private String filename;
	
	private LinkedList<Integer> buffer;
	private int buffer_size;
	
	public M_stream_implementation3_write (String path, String filename, String extension, int b) {
		 this.path = path;
		 this.ext=extension;
		 File d = new File(path);
		   // Create directory now.
		   d.mkdirs();
		   this.filename=filename;
		   this.buffer_size=b;
		 this.buffer = new LinkedList<Integer>();

	}
	
	public void create() throws FileNotFoundException{

		  os = new FileOutputStream(new File(path + filename +ext)  );
		 dos = new DataOutputStream(os); 
	}
	
	public void write(Integer integ) throws IOException{
		
		buffer.add(integ);
		 if (buffer.size()==buffer_size){
			for(int i =0; i<buffer_size;i++){
				 dos.writeInt(buffer.poll());
			}
		}
	}
	
	
	public void close() throws IOException{
		 if (buffer.size()!=0){
				while(buffer.size()!=0){
					 dos.writeInt(buffer.poll());
				}
			}
		dos.close();
		os.close();
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

		
		
		M_stream_implementation3_write w = new M_stream_implementation3_write("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "newtest_0",".data", 4);
		w.create();

		while(m.has_more_values()){
			w.write(m.read_next());

		}
		w.close();
		m.close();

		M_stream_implementation3_read q = new M_stream_implementation3_read("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "newtest_0",".data", 4);
		//writes 32 bits - 8 integers
		q.open();

		while(q.has_more_values()){
			System.out.println(q.read_next());

		}
		q.close();

	}

}
