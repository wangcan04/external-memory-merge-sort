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

public class M_stream_implementation2_read  implements M_readStream{
	
	private InputStream is;
	private BufferedInputStream bis;
	private DataInputStream ds;
	
	private String path;
	
	private String filename;
	private String ext;
	
	
	public M_stream_implementation2_read (String path, String filename, String extension) {
		this.path = path;
		this.ext=extension;
		 File d = new File(path);
		   // Create directory now.
		   d.mkdirs();
		   this.filename=filename;
	}
	
	public void open() throws IOException{

		
		
		 is = new FileInputStream( new File(path + filename + ext) );
			//System.out.println(is);
		 bis = new BufferedInputStream( is );
			//System.out.println(bis);
		 ds = new DataInputStream( bis );
			//System.out.println(ds);
	}
	
	
	public Integer read_next() throws IOException {
		 //is = new FileInputStream( new File("input.data" ) );
		int var = ds.readInt();
		
		 return var;
				
	}
	
	
	public void close() throws IOException{
		ds.close();
		is.close();
	
	}
	
	
	public boolean end_of_stream() throws IOException{
		if (ds.available()>0){
			//System.out.println("true");
			return false;
		}
		
		else return true;
	}
	
	public boolean has_more_values() throws IOException{
		if (ds.available()>0){
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

	
	

}
