import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class M_mway_merge_sort {
	
	private String path;
	private String filename;
	private String ext;
	//size of main memory
	private int m;
	//number of streams to merge in each pass
	private int d;
	//size of the input file
	private int n;
	
	private int n_sublists_created;
	
	private int n_passes;
	
	//Initiate a read and a write stream (implementation 2)
	private M_stream_implementation2_read readStream; 
	
	private LinkedList <M_stream_implementation2_write> outArray;

	
	
	private PriorityQueue<Integer> pri_main_memory;
	
	
	public M_mway_merge_sort(String path, String filename, String extension, int m, int d){
		this.path=path;
		this.filename=filename;
		this.ext=extension;
		//size of main memory in number of tuples
		this.m=m;
		//number of streams to merge in one pass in the later sort phases
		this.d=d;
		
		//stream to read the input file of the first phase (unsorted sublists)
		readStream= new M_stream_implementation2_read(path, filename,ext);
		//write several - array with n_sublists_created positions
		//read several - array with n_sublists_created positions
		//stream to write the output files of the first phase (sorted sublists)
//		this.writeStream= new M_stream_implementation2_write(path, "MergedSortedList",ext);


		this.n_sublists_created=0;
		this.n =0;
	}
	
	public void Just_sort (String outputPrefix) throws IOException{
		Integer aux=0;
	    pri_main_memory = new PriorityQueue<Integer>();
		readStream.open();
		outArray = new LinkedList<M_stream_implementation2_write>();
		
		while (readStream.has_more_values()){
			
			//sort
			System.out.println(n_sublists_created+" Sublist UNordered:");
			for(int i=0; i<m;i++){
				if(readStream.has_more_values()){
						aux=readStream.read_next();
					//	System.out.println(aux);
						pri_main_memory.add(aux);
						n++;
				}
			
			
			}
			//write
			System.out.println(n_sublists_created+"Ordered Sublist:");
			outArray.add(new M_stream_implementation2_write(path,(outputPrefix+"_"+n_sublists_created),ext));
			outArray.get(n_sublists_created).create();
			for(int i=0; i<m;i++){
				if(pri_main_memory.isEmpty()==false){
					//System.out.println(pri_main_memory.peek());
					outArray.get(n_sublists_created).write(pri_main_memory.poll());
				}
				
				
			}
			
			n_sublists_created++;
		}
		for(int i=0; i<n_sublists_created;i++){
			outArray.get(i).close();
		}
		
		System.out.println("Total of Integers: "+ n+ ", n_lists_created: " +n_sublists_created );
		System.out.println("M:" +m+", N: "+n+", d: "+d);
	}
	
	
	//sorting with a priority queue
	public void Read_and_sort(String outputPrefix) throws IOException{
		Integer aux=0;
	    pri_main_memory = new PriorityQueue<Integer>();
		readStream.open();
		outArray = new LinkedList<M_stream_implementation2_write>();
		
		while (readStream.has_more_values()){
			
			//sort
			System.out.println(n_sublists_created+" Sublist UNordered:");
			for(int i=0; i<m;i++){
				if(readStream.has_more_values()){
						aux=readStream.read_next();
					//	System.out.println(aux);
						pri_main_memory.add(aux);
						n++;
				}
			
			
			}
			//write
			System.out.println(n_sublists_created+"Ordered Sublist:");
			outArray.add(new M_stream_implementation2_write(path,(outputPrefix+"_"+n_sublists_created),ext));
			outArray.get(n_sublists_created).create();
			for(int i=0; i<m;i++){
				if(pri_main_memory.isEmpty()==false){
					//System.out.println(pri_main_memory.peek());
					outArray.get(n_sublists_created).write(pri_main_memory.poll());
				}
				
			}
			n_sublists_created++;
		}
		
		System.out.println("Total of Integers: "+ n+ ", n_lists_created: " +n_sublists_created );
		System.out.println("M:" +m+", N: "+n+", d: "+d);
		
		
		
		//Create read array to send for the first pass
		System.out.println("Create read array to send for the first pass");
		LinkedList<M_stream_implementation2_read> readArray = new LinkedList<M_stream_implementation2_read>();
		for(int i=0; i<outArray.size();i++){
			readArray.add(new M_stream_implementation2_read(path, outArray.get(i).return_filename(),ext));
			//System.out.println("MERGE SORT - 1st pass "+path+outArray.get(i).return_filename()+ext);
			outArray.get(i).close();
			
		}
		
		merge(1,readArray);
		
	}
	

	
	//should be recursive
	//while has more sublists to merge keeps merging
	//after the current pass if there are more than one sublists, do another pass
	public void merge(int step, LinkedList<M_stream_implementation2_read>  readA ) throws IOException{
		int pointer=0;
		int n_read =readA.size();
		int n_merge_of_step =0;
		M_multiway_merge m= new M_multiway_merge(path,".data");
		LinkedList<M_stream_implementation2_read>  tomerge_next_step= new LinkedList<M_stream_implementation2_read>();
		LinkedList<M_stream_implementation2_read>  read;
		
		String file_created_name;
		
		//while there are sublists to merge
		while(pointer <n_read){
		    read = new LinkedList<M_stream_implementation2_read>();
			for(int i=0; i<d; i++){
				if(readA.isEmpty()==false){
					read.add(readA.poll());		
					pointer++;
				}
				
			}
			//merge
			n_merge_of_step++;
			
			file_created_name=m.merge_v3(read, step, n_merge_of_step);

			tomerge_next_step.add(new M_stream_implementation2_read(path, file_created_name,ext));
			System.out.println(file_created_name);
		}
			
			
		//if the number of streams created is more than 1 -- n_merge_of_step>1
		//Create a linkedlist of the read streams to merge
		if(n_merge_of_step>1){
			//"merge_output_step"+step+"_"+n_merge_of_step
			merge(step+1,tomerge_next_step);
		}
		if(n_merge_of_step==1){
		//	System.out.println(path);
			System.out.println("merge_output_step"+step+"_"+n_merge_of_step);
		//	print_file(path,"merge_output_step"+step+"_"+n_merge_of_step);
		}
		
		
	}
	
public void print_file(String path, String filename) throws IOException{
		
		LinkedList<Integer> toread2=new LinkedList<Integer>();
		M_stream_implementation2_read test_r2 = new M_stream_implementation2_read(path, filename, ext);
		test_r2.open();
		
		int x=0;
		
		while( test_r2.has_more_values()==true ){
			toread2.add(test_r2.read_next());
			System.out.println(toread2.get(x));
			x++;
		}
		test_r2.close();
	}
	
	

}
