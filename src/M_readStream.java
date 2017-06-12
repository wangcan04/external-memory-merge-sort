import java.io.FileNotFoundException;
import java.io.IOException;

public interface M_readStream {
	
	public void open() throws IOException;
	public Integer read_next()  throws IOException;
	public void close()  throws IOException;
	public boolean end_of_stream() throws IOException;
	public boolean  has_more_values() throws IOException;
	public String return_filename();
	public String return_path();

}
