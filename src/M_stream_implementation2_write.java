import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class M_stream_implementation2_write implements M_writeStream{
	

	private OutputStream os;
	private BufferedOutputStream bos;
	private DataOutputStream dos;
	
	private String path;
	private String ext;
	
	private String filename;
	
	
	public M_stream_implementation2_write (String path, String filename, String extension) {
		 this.path = path;
		 this.ext=extension;
		 File d = new File(path);
		   // Create directory now.
		   d.mkdirs();
		   this.filename=filename;
	}
	
	public void create() throws IOException{
		

		
		  os = new FileOutputStream(new File(path + filename +ext)  );
		  bos = new BufferedOutputStream( os );
		 dos = new DataOutputStream(bos); 
			//System.out.println(os);
			//System.out.println(bos);
			//System.out.println(dos);
	}
	
	public void write(Integer integ) throws IOException{
		 //dos.writeByte(integ);
		dos.writeInt(integ);
		 //dos.writeUTF(integ.toString());
		 
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
