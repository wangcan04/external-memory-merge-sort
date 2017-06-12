import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class M_multiway_merge {
	
	//folder of the project
	private String path;
	
	//prefix of the file(s) name(s)
	//private String filename;
	
	//extension
	private String extension;
	

	
	
	//private 	LinkedList<M_stream_implementation2_read> l;

	

	
	public M_multiway_merge( String path, String ext){
		this.path=path;
		//this.filename=prefix;
		this.extension=ext;
	}
	


	
	//Merging reading from streams and working with one tuple of each in main memory
	//Receives as input  a Linked List with the input streams
		public String merge_v3(LinkedList<M_stream_implementation2_read>  ll, int step, int n_merge_of_step) throws IOException{
			
			//linked list of read stream - 2nd implementation
			LinkedList<M_stream_implementation2_read>  l=ll;
			int d= l.size();	
			
			System.out.println();
			System.out.println("Merge files -d- " + d);
			
			//priority queue to implement a d-way merge
			Integer [] main_memory = new Integer [d];
			Integer Outputbuffer=null;
			int index;

			
			int q =0;
			//Open all
			for( q=0; q<d; q++){
				l.get(q).open();
			}
			q--;
			
			//Output stream
			M_stream_implementation2_write w = new M_stream_implementation2_write(path, ("merge_output_step"+step+"_"+n_merge_of_step), extension);
			w.create();
			
			
			//check the streams created
			for(int e=0; e<d; e++){
				System.out.println(l.get(e));
			}
			/////Merge all
			//First put one of each stream in main memory
			for(int i=0; i<d;i++){
				main_memory[i]= l.get(i).read_next() ;	
			}

			//while at least one of the streams has values to output
			while(has_more_to_output(l) || has_values_in_mem(main_memory)){
		
				index=0;

				//open with the first value not null to be able to proceed
				//once it finds a value to start breaks
				for(int i=0; i<d;i++){
					if (main_memory[i]!=null){
						Outputbuffer=main_memory[i];
						index=i;
						i=d;
					}
					
				}
				//decide on the smaller
				for(int i=index; i<d;i++){	
					if(main_memory[i]!=null ) {
						if(main_memory[i]<Outputbuffer ){
							Outputbuffer=main_memory[i];
							index =i;
						}
						
					}
					
				}
				/**
				//print the memory
				System.out.println("-------------------");
				for(int i=0; i<d;i++){				
					System.out.println(main_memory[i]);
				}
				System.out.println("-------------------");
				**/
				w.write(Outputbuffer);
				Outputbuffer=null;
				if(l.get(index).has_more_values()){
					main_memory[index]= l.get(index).read_next() ;	
				}
				else if(l.get(index).has_more_values()== false){
					main_memory[index]= null ;	
				}
				
			}
			
			//Close all
			for(int e=0; e<d; e++){
				l.get(e).close();
			}
			
			w.close();
			//print_file(path,"merge_output_step"+step+"_"+l.get(0).return_filename()+"_"+l.get(q).return_filename());
			//print_file(path, ("merge_output_step"+step+"_"+n_merge_of_step));
			String filename=("merge_output_step"+step+"_"+n_merge_of_step);
			return filename;

		}
	
		
	
	public boolean has_more_to_output(LinkedList<M_stream_implementation2_read> l ) throws IOException{
		for (int i=0; i< l.size(); i++){
			if(l.get(i).has_more_values()==true){
				return true;
			}
		}
		return false;
	}
	
	public boolean has_values_in_mem(Integer[] mem){
		for (int i=0; i<mem.length;i++){
			if (mem[i] !=null){
				return true;
			}
		}
		return false;
	}
	
	
	public void print_file(String path, String filename) throws IOException{
		
		LinkedList<Integer> toread2=new LinkedList<Integer>();; 
		M_stream_implementation2_read test_r2 = new M_stream_implementation2_read(path, filename, extension);
		test_r2.open();
		
		int x=0;
		
		while( test_r2.has_more_values()==true ){
			toread2.add(test_r2.read_next());
			System.out.println(toread2.get(x));
			x++;
		}
		test_r2.close();
	}
	
	
	
	
	/**
	public static void main(String[] args) throws IOException {
		M_multiway_merge m= new M_multiway_merge("C:\\Users\\Mariana\\Desktop\\architecture_test\\", ".data");
		//CREATES THE FILES NEEDED first - file generator!!!!

		
		//m.merge_v2(4,0);
		//m.merge_v2(3,0);
		LinkedList<M_stream_implementation2_read>  ll = new LinkedList<M_stream_implementation2_read>();
		
		for (int i=0; i<3;i++){
			ll.add(new M_stream_implementation2_read("C:\\Users\\Mariana\\Desktop\\architecture_test\\", "orderedFile_"+i,".data"));
		}
		m.merge_v3(ll,1,1);
	}
	**/

}
