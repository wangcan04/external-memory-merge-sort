import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class M_stream_implementation1_write implements M_writeStream{
	

	private OutputStream os;
	private DataOutputStream dos;
	
	private String path;
	
	private String filename;
	private String ext;
	
	public M_stream_implementation1_write (String path, String filename, String extension) throws Exception {
		this.filename=filename;
		 this.path = path;
		 this.ext=extension;
		 File d = new File(path);
		   // Create directory now.
		   d.mkdirs();
	}
	
	public void create() throws IOException{
		

		  os = new FileOutputStream(new File(path + filename +ext)  );
		 dos = new DataOutputStream(os); 
		// System.out.println(os);
		// System.out.println(dos);
	}
	




	
	public void write(Integer integ) throws IOException{
		 dos.write(integ);
		 
	}
	
	
	public void close() throws IOException{
		dos.close();
		os.close();
	}
	
	
	public String return_filename(){
		return filename;
	}
	
	public String return_path(){
		return path;
	}
	
	
	

}
