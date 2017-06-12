import java.io.FileNotFoundException;
import java.io.IOException;

public interface M_writeStream {
	
	public void create() throws IOException;
	public void write(Integer integ) throws IOException;
	public void close() throws IOException;
	public String return_filename();
	public String return_path();

}
