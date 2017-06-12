import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class M_main {
	
	private int size;
	private String path;
	private String filename; 
	private String extension;
	private LinkedList<Integer> towrite;
	
	private LinkedList<Integer>  toread1; 
	private LinkedList<Integer> toread2; 

	
	public M_main(int size, String path, String filename, String extension){
		this.size=size;
		this.path=path;
		this.filename=filename;
		this.extension=extension;
		toread1 = new LinkedList<Integer>();
		toread2 = new LinkedList<Integer>();

		
		//create_file();
		create_List_to_write_file();
	}
	
	
	public void create_List_to_write_file(){
		towrite = new LinkedList<Integer>();


		//Create a new file with "size" integers
		Random r = new Random();	
		for (int i =0; i<size;i++){
			towrite.add(Integer.valueOf(r.nextInt()));
		}
	}

	//run one stream of write and one of read, having "size" integers read/written - 1st implementation
	public void run1() throws Exception{
		 System.out.println("---------------1-------------------");		
		 System.out.println("Reading (1 stream) and writing (1 stream) in one file " + size + " values");
			// test 1st implementation			
				M_stream_implementation1_write test_w1 = new M_stream_implementation1_write(path, filename+"_1", extension);
				test_w1.create();
				for( int i=0; i<size;i++){
					test_w1.write(towrite.get(i));
			//		System.out.println(towrite.get(i));
				}
				test_w1.close();
			
				M_stream_implementation1_read test_r1 = new M_stream_implementation1_read(path, filename+"_1", extension);
				test_r1.open();
			
				int x=0;
				while( test_r1.has_more_values()==true ){
					toread1.add(test_r1.read_next());
				//	System.out.println(toread1.get(x));
					x++;
				}

				test_r1.close();
	}
	
	//run one stream of write and one of read, having "size" integers read/written - 2nd implementation
	public void run2() throws IOException{
		System.out.println();
		  System.out.println("---------------2-------------------");	
		// test 2nd implementation			
		M_stream_implementation2_write test_w2 = new M_stream_implementation2_write(path, filename+"_2", extension);
		test_w2.create();

		for( int i=0; i<size;i++){
			test_w2.write(towrite.get(i));
		//	System.out.println(towrite.get(i));
		}
		test_w2.close();
	
		M_stream_implementation2_read test_r2 = new M_stream_implementation2_read(path, filename+"_2", extension);
		test_r2.open();
	

		int x=0;
		while( test_r2.has_more_values()==true ){
			toread2.add(test_r2.read_next());
	//		System.out.println(toread2.get(x));
			x++;
		}
		test_r2.close();
	}

	
	
	public void test_implementation1_write(int k_streams, int N_elements) throws Exception{
		//System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " times");
		LinkedList<M_stream_implementation1_write> streams = new LinkedList<M_stream_implementation1_write>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation1_write(path, filename+"_imp_1_stream"+i, extension));
			streams.get(i).create();
		}
		for (int i=0; i<N_elements;i++){
				for (int j=0; j<k_streams;j++){
					streams.get(j).write(towrite.get(i));
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	public void test_implementation1_read(int k_streams, int N_elements) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " times");
		LinkedList<M_stream_implementation1_read> streams = new LinkedList<M_stream_implementation1_read>();
		LinkedList<Integer> read = new LinkedList<Integer>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation1_read(path, filename+"_imp_1_stream"+i, extension));
			streams.get(i).open();
		}
		for (int i=0; i<N_elements;i++){
						for (int j=0; j<k_streams;j++){
					if(streams.get(j).has_more_values()){
						read.add(streams.get(j).read_next());
					}
					
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	
	public void test_implementation2_write(int k_streams, int N_elements) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " times");
		LinkedList<M_stream_implementation2_write> streams = new LinkedList<M_stream_implementation2_write>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation2_write(path, filename+"_imp_2_stream"+i, extension));
			streams.get(i).create();
		}
		for (int i=0; i<N_elements;i++){
				for (int j=0; j<k_streams;j++){
					streams.get(j).write(towrite.get(i));
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	public void test_implementation2_read(int k_streams, int N_elements) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " times");
		LinkedList<M_stream_implementation2_read> streams = new LinkedList<M_stream_implementation2_read>();
		LinkedList<Integer> read = new LinkedList<Integer>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation2_read(path, filename+"_imp_2_stream"+i, extension));
			streams.get(i).open();
		}
		for (int i=0; i<N_elements;i++){
						for (int j=0; j<k_streams;j++){
					if(streams.get(j).has_more_values()){
						read.add(streams.get(j).read_next());
					}
					
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	
	public void test_implementation3_write(int k_streams, int N_elements, int B) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " times");
		LinkedList<M_stream_implementation3_write> streams = new LinkedList<M_stream_implementation3_write>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation3_write(path, filename+"_imp_3_stream"+i, extension, B));
			streams.get(i).create();
		}
		for (int i=0; i<N_elements;i++){
				for (int j=0; j<k_streams;j++){
					streams.get(j).write(towrite.get(i));
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	public void test_implementation3_read(int k_streams, int N_elements, int B) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " times");
		LinkedList<M_stream_implementation3_read> streams = new LinkedList<M_stream_implementation3_read>();
		LinkedList<Integer> read = new LinkedList<Integer>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation3_read(path, filename+"_imp_3_stream"+i, extension, B));
			streams.get(i).open();
		}
		for (int i=0; i<N_elements;i++){
						for (int j=0; j<k_streams;j++){
					if(streams.get(j).has_more_values()){
						read.add(streams.get(j).read_next());
					}
					
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	

	public void test_implementation4_write(int k_streams, int N_elements, int b) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " times");
		LinkedList<M_stream_implementation4_write> streams = new LinkedList<M_stream_implementation4_write>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation4_write(path, filename+"_imp_4_stream"+i, extension,b));
			streams.get(i).create();
		}
		for (int i=0; i<N_elements;i++){
				for (int j=0; j<k_streams;j++){
					streams.get(j).write(towrite.get(i));
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	public void test_implementation4_read(int k_streams, int N_elements, int b) throws IOException{
		//System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " times");
		LinkedList<M_stream_implementation4_read> streams = new LinkedList<M_stream_implementation4_read>();
		LinkedList<Integer> read = new LinkedList<Integer>();
		for (int i=0; i<k_streams;i++){
			streams.add(new M_stream_implementation4_read(path, filename+"_imp_4_stream"+i, extension,b));
			streams.get(i).open();
		}
		for (int i=0; i<N_elements;i++){
						for (int j=0; j<k_streams;j++){
					if(streams.get(j).has_more_values()){
						read.add(streams.get(j).read_next());
					}
					
			}
		}
		for (int i=0; i<k_streams;i++){
			streams.get(i).close();
		}
	}
	
	
	
	
	
	
	//do the test (read and write) for k streams opened and N values read/written - 1st implementation
	public void test1_average(int k_streams, int N_elements) throws Exception{
		int n_runs=1;
		System.out.println("-----------1st implementation test-----------");
		System.out.println(" - Test with " + n_runs + " runs - ");
 		System.out.println();
		System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " elements ");
		
		long start;
 		long end;
 		long total_time=0;
 		
 		start= System.currentTimeMillis();
 		for (int i=0; i<n_runs;i++){
 	 		test_implementation1_write(k_streams,N_elements);
 	 	
 		}	 end = System.currentTimeMillis();
 	      	total_time=  (end - start);
 	      	//System.out.println((end - start) + " ms");

 		System.out.println("Average writting running time: " + total_time/n_runs + " ms");
 		System.out.println();

		System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " elements ");
 		total_time=0;
 		for (int i=0; i<n_runs;i++){
 			start= System.currentTimeMillis();
 	 		test_implementation1_read(k_streams,N_elements);
 	 		 end = System.currentTimeMillis();
 	      	total_time= total_time + (end - start);
 	      	//System.out.println((end - start) + " ms");
 		}
 		System.out.println("Average reading running time: " + total_time/n_runs  + " ms");
 		System.out.println();
	}
	
	//do the test (read and write) for k streams opened and N values read/written - 2nd implementation
	public void test2_average(int k_streams, int N_elements) throws IOException{
		int n_runs=1;
		System.out.println("-----------2nd implementation test-----------");
		System.out.println(" - Test with " + n_runs + " runs - ");
 		System.out.println();
		System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " elements ");
		
		long start;
 		long end;
 		long total_time=0;
 		start= System.currentTimeMillis();
 		for (int i=0; i<n_runs;i++){
 			
 	 		test_implementation2_write(k_streams,N_elements);
 	 		
 		} 
 		end = System.currentTimeMillis();
 	      	total_time=  (end - start);
 	      	//System.out.println((end - start) + " ms");

 		System.out.println("Average writting running time: " + total_time/n_runs + " ms");
 		System.out.println();

		System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " elements ");
 		total_time=0;
 		for (int i=0; i<n_runs;i++){
 			start= System.currentTimeMillis();
 	 		test_implementation2_read(k_streams,N_elements);
 	 		 end = System.currentTimeMillis();
 	      	total_time= total_time + (end - start);
 	      	//System.out.println((end - start) + " ms");
 		}
 		System.out.println("Average reading running time: " + total_time/n_runs  + " ms");
 		System.out.println();
	}
	
	public void test3_average(int k_streams, int N_elements, int b) throws IOException{
		int n_runs=1;
		System.out.println("-----------3rd implementation test-----------");
		System.out.println(" - Test with " + n_runs + " runs - ");
 		System.out.println();
		System.out.println("Opening " + k_streams + " streams and writing " + N_elements + " elements; buffer size: " + b);
		
		long start;
 		long end;
 		long total_time=0;
 		start= System.currentTimeMillis();
 		for (int i=0; i<n_runs;i++){
 	 		test_implementation3_write(k_streams,N_elements,b);
 	 		 
 	      
 		}
 		end = System.currentTimeMillis();
 	    total_time= (end - start);
 	    	//System.out.println((end - start) + " ms");
 		System.out.println("Average writting running time: " + total_time/n_runs + " ms");
 		System.out.println();

		System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " elements ; buffer size: " + b);
 		total_time=0;
 		for (int i=0; i<n_runs;i++){
 			start= System.currentTimeMillis();
 	 		test_implementation3_read(k_streams,N_elements,b);
 	 		 end = System.currentTimeMillis();
 	      	total_time= total_time + (end - start);
 	      	//System.out.println((end - start) + " ms");
 		}
 		System.out.println("Average reading running time: " + total_time/n_runs  + " ms");
 		System.out.println();
	}
	
	
	public void test4_average(int k_streams, int N_elements, int b) throws IOException{
		int n_runs=1;
		System.out.println("-----------4th implementation test-----------");
		System.out.println(" - Test with " + n_runs + " runs - ");
 		System.out.println();
		System.out.println("Opening " + k_streams + " streams and writing " + N_elements +  " elements; buffer size: " + b);
		
		long start;
 		long end;
 		long total_time=0;
 		start= System.currentTimeMillis();
 		for (int i=0; i<n_runs;i++){
 	 		test_implementation4_write(k_streams,N_elements,b);
 	 		 
 	      
 		}
 		end = System.currentTimeMillis();
 	    total_time= (end - start);
 	    	//System.out.println((end - start) + " ms");
 		System.out.println("Average writting running time: " + total_time/n_runs + " ms");
 		System.out.println();

		System.out.println("Opening " + k_streams + " streams and reading " + N_elements + " elements; buffer size: " + b);
 		total_time=0;
 		for (int i=0; i<n_runs;i++){
 			start= System.currentTimeMillis();
 	 		test_implementation4_read(k_streams,N_elements,b);
 	 		 end = System.currentTimeMillis();
 	      	total_time= total_time + (end - start);
 	      	//System.out.println((end - start) + " ms");
 		}
 		System.out.println("Average reading running time: " + total_time/n_runs  + " ms");
 		System.out.println();
	}
	
	
	public static void main(String[] args) throws Exception {
		M_main m = new M_main(1000000, "C:\\Users\\Mariana\\Desktop\\architecture_test\\", "Streams_test",".data");
       
		long start;
 		long end;
 		
 		
		//////////////////////////Make the average of each read/write implementation//////////////////////
 		
 		//1st parameter - k; 2nd parameter - N
 		m.test1_average(1, 10000);
		m.test1_average(10, 10000);
		m.test1_average(30, 10000);
		m.test1_average(5, 100);
		m.test1_average(5, 10000);
 		m.test1_average(5, 100000);	
 		/**
 		//1st parameter - k; 2nd parameter - N
 		m.test2_average(1, 10000);
		m.test2_average(10, 10000);
		m.test2_average(30, 10000);
		m.test2_average(5, 100);
		m.test2_average(5, 10000);
 		m.test2_average(5, 100000);	
 		
 		//1st parameter - k; 2nd parameter - N; 3rd parameter - B
 		m.test3_average(1, 10000,1000);
		m.test3_average(10, 10000, 1000);
		m.test3_average(30, 10000, 1000);
		m.test3_average(5, 100, 1000);
		m.test3_average(5, 10000,1000);
 		m.test3_average(5, 100000,1000);	
		m.test3_average(5, 10000,2);
		m.test3_average(5, 10000,500);
 		m.test3_average(5, 10000,10000);	
 		
 		//1st parameter - k; 2nd parameter - N; 3rd parameter - B
 		m.test4_average(1, 10000,1000);
		m.test4_average(10, 10000, 1000);
		m.test4_average(30, 10000, 1000);
		m.test4_average(5, 100, 1000);
		m.test4_average(5, 10000,1000);
 		m.test4_average(5, 100000,1000);	
		m.test4_average(5, 10000,2);
		m.test4_average(5, 10000,500);
 		m.test4_average(5, 10000,10000);
 		
 		**/
 		
 		/**
		//////////////////////////Make one test of each read/write implementation//////////////////////
 		int k=10;
 		int n =10000;
 		int b=5000;
 		  start= System.currentTimeMillis();
		m.test_implementation1_write(k, n);
		 end = System.currentTimeMillis();
		 System.out.println("---------Runtime - 1 - write----------------");
	        System.out.println((end - start) + " ms");
		 
		 start= System.currentTimeMillis();
		m.test_implementation1_read(k, n);
		 end = System.currentTimeMillis();
		 System.out.println("---------Runtime - 1 - read----------------");
	        System.out.println((end - start) + " ms");
		 
		 start= System.currentTimeMillis();
		m.test_implementation2_write(k,n);
		 end = System.currentTimeMillis();
		 System.out.println("---------Runtime - 2 - write----------------");
	        System.out.println((end - start) + " ms");
		 
		 start= System.currentTimeMillis();
		m.test_implementation2_read(k,n);
		 end = System.currentTimeMillis();
		 System.out.println("---------Runtime - 2 - read----------------");
	        System.out.println((end - start) + " ms");
	        
	   	 start= System.currentTimeMillis();
			m.test_implementation3_write(k, n, b);
			 end = System.currentTimeMillis();
			 System.out.println("---------Runtime - 3 - write----------------");
		        System.out.println((end - start) + " ms");
			 
			 start= System.currentTimeMillis();
			m.test_implementation3_read(k, n, b);
			 end = System.currentTimeMillis();
			 System.out.println("---------Runtime - 3 - read----------------");
		        System.out.println((end - start) + " ms");
				
		        start= System.currentTimeMillis();
				m.test_implementation4_write(k, n, b);
				end = System.currentTimeMillis();
				System.out.println("---------Runtime - 4 - write----------------");
				System.out.println((end - start) + " ms");
		        
		start= System.currentTimeMillis();
		m.test_implementation4_read(k, n, b);
		end = System.currentTimeMillis();
		System.out.println("---------Runtime - 4 - read----------------");
		System.out.println((end - start) + " ms");
 		**/
 		
 		/** 
 		 * 
 		//////////////////////////////Test one stream of read followed by one stream of write of each implementation////////////////
 		///////////////////////////  1     - read and write
        start= System.currentTimeMillis();
        m.run1();
        end = System.currentTimeMillis();
        System.out.println("---------Runtime - 1 - write one file and read one file----------------");
        System.out.println((end - start) + " ms");
        
        
        //////////////    2 - read and write
        start= System.currentTimeMillis();
		m.run2();
        end = System.currentTimeMillis();
        System.out.println("Runtime - 2 -----------------");
        System.out.println((end - start) + " ms");
     
		**/
      	
	}

}
