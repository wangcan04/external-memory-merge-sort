import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class M_file_generator {
	
	
	//Uses the 2nd implementation to write
	public void create_multiple_ordered_files(int n_files, int begining_nbr, String path, String filename, String extension, int size ) throws IOException{
		System.out.println();
		System.out.println("Create multiple files: " + n_files);
		
		//Using a priority queue to order the random Integers
		PriorityQueue<Integer> towritemultipleordered = new PriorityQueue<Integer>();
		M_stream_implementation2_write w;
	
		int beg = begining_nbr;
		
		int q =0;
		
		for(q=0; q<n_files; q++){
			//Create a new file with 'size' integers
			w=new M_stream_implementation2_write(path, (filename+"_"+beg),extension);
			Random r = new Random();	
			for (int i =0; i<size;i++){
				towritemultipleordered.add(Integer.valueOf(r.nextInt()));
			}
			w.create();
	
			for( int i=0; i<size;i++){
				System.out.println(towritemultipleordered.peek());
				w.write(towritemultipleordered.poll());
				
			}
			beg++;
			w.close();
		}
		
	}
	
	
	//Uses the 2nd implementation to write	
	public void create_multiple_unordered_files(int n_files, int begining_nbr, String path, String filename, String extension, int size ) throws IOException{
		System.out.println();
		System.out.println("Create multiple files: " + n_files);

		//Using a linked list not to order the random Integers
		LinkedList<Integer> towritemultipleUNordered = new LinkedList<Integer>();
		
		M_stream_implementation2_write w;
	
		int beg = begining_nbr;
		
		int q =0;
		
		for(q=0; q<n_files; q++){
			//Create a new file with 'size' integers
			w=new M_stream_implementation2_write(path, (filename+"_"+beg),extension);
			Random r = new Random();	
			for (int i =0; i<size;i++){
				towritemultipleUNordered.add(Integer.valueOf(r.nextInt()));
			}
			w.create();
	
			for( int i=0; i<size;i++){
				System.out.println(towritemultipleUNordered.peek());
				w.write(towritemultipleUNordered.poll());
				
			}
			beg++;
			w.close();
		}
		
	}
	
	//Uses the 2nd implementation to read
	public void read_file(String path, String filename, String ext) throws IOException{
		
		LinkedList<Integer> toread2=new LinkedList<Integer>();; 
		M_stream_implementation2_read test_r2 = new M_stream_implementation2_read(path, (filename),ext);
		test_r2.open();
		System.out.println("File: " + filename + ext);
		int x=0;
		while( test_r2.has_more_values()==true ){
			toread2.add(test_r2.read_next());
			System.out.println(toread2.get(x));
			x++;
		}
		test_r2.close();
	}
	
	//Uses the 2nd implementation to read
	public void read_multiple_files(int n_files, int begining, String path, String filename, String ext) throws IOException{
		//Create the values to write (linkedlist) and 3 queues: to read from a file
		M_stream_implementation2_read r;

		
		for(int q=0; q<n_files; q++){
			r= new M_stream_implementation2_read(path, (filename+"_"+(q+begining)),ext);
			r.open();
			System.out.println("File: " + filename+"_"+(q+begining) + ext);
			while(r.has_more_values()){
				System.out.println(r.read_next()+"");				
			}
			r.close();
		}
		System.out.println();
	}
	
	

	public static void main(String[] args) throws IOException {
		
	
		//test the methods
		M_file_generator m = new M_file_generator();
		/**
		m.read_multiple_files(2, 0, "C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data");
		**/
		m.create_multiple_ordered_files(3, 0,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile", ".data", 15);
		m.create_multiple_unordered_files(1, 0,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data", 100);
		m.create_multiple_unordered_files(1, 1,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data", 1000);
		m.create_multiple_unordered_files(1, 2,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data", 10000);
		m.create_multiple_unordered_files(1, 3,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data", 100000);
		m.create_multiple_unordered_files(1, 4,"C:\\Users\\Mariana\\Desktop\\architecture_test\\", "unorderedFile", ".data", 1000000);
	//	m.read_multiple_files(2, 20, "C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile", ".data");
	//	m.read_file("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile_1", ".data");
	}

}
