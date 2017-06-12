import java.io.IOException;

public class M_mway_merge_sort_testing {

	public static void main(String[] args) throws IOException {
		long start;
 		long end;
 		int n_tests=21;
 		long[]times= new long[n_tests];
 		String path ="C:\\Users\\Mariana\\Desktop\\architecture_test\\";
 		
 		///////////////////N/////////////////////////////////////////
 		//path, filename, extension, M (10), d (10)
		//unorderedFile_0 - 100 Integers	
		M_mway_merge_sort msort0 = new M_mway_merge_sort(path, "unorderedFile_0", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort0.Read_and_sort("sortedSublist_t0");
		 		end = System.currentTimeMillis();
		 		times[0]=(end - start);

		//unorderedFile_1 - 1000 Integers 
				M_mway_merge_sort msort1 = new M_mway_merge_sort(path, "unorderedFile_1", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort1.Read_and_sort("sortedSublist_t1");
		 		end = System.currentTimeMillis();
		 		times[1]=(end - start);
		 		
		//unorderedFile_2 - 10000 Integers
				M_mway_merge_sort msort2 = new M_mway_merge_sort(path, "unorderedFile_2", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort2.Read_and_sort("sortedSublist_t2");
		 		end = System.currentTimeMillis();
		 		times[2]=(end - start);
		 		
		//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort3 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort3.Read_and_sort("sortedSublist_t3");
		 		end = System.currentTimeMillis();
		 		times[3]=(end - start);
		 		
		//unorderedFile_4 - 1000000 Integers
				M_mway_merge_sort msort4 = new M_mway_merge_sort(path, "unorderedFile_4", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort4.Read_and_sort("sortedSublist_t4");
		 		end = System.currentTimeMillis();
		 		times[4]=(end - start);

		 
		 /////////////////////M//////////////////////////////////////////	
		 	//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort5 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 50, 10);
		 		start= System.currentTimeMillis();
		 		msort5.Read_and_sort("sortedSublist_t5");
		 		end = System.currentTimeMillis();
		 		times[5]=(end - start);
		 		
		 	//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort6 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 500, 10);
		 		start= System.currentTimeMillis();
		 		msort6.Read_and_sort("sortedSublist_t6");
		 		end = System.currentTimeMillis();
		 		times[6]=(end - start);
		 		
		 	//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort7 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 10);
		 		start= System.currentTimeMillis();
		 		msort7.Read_and_sort("sortedSublist_t7");
		 		end = System.currentTimeMillis();
		 		times[7]=(end - start);
		 		
		 		
		 	//////
		 		//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort8 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 50, 100);
		 		start= System.currentTimeMillis();
		 		msort8.Read_and_sort("sortedSublist_t8");
		 		end = System.currentTimeMillis();
		 		times[8]=(end - start);
		 		
		 	//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort9 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 500, 100);
		 		start= System.currentTimeMillis();
		 		msort9.Read_and_sort("sortedSublist_t9");
		 		end = System.currentTimeMillis();
		 		times[9]=(end - start);
		 		
		 	//unorderedFile_3 - 100000 Integers
				M_mway_merge_sort msort10 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 100);
		 		start= System.currentTimeMillis();
		 		msort10.Read_and_sort("sortedSublist_t10");
		 		end = System.currentTimeMillis();
		 		times[10]=(end - start);

		 		
			/////////////////////d//////////////////////////////////////////	
			 	//unorderedFile_3 - 100000 Integers
					M_mway_merge_sort msort11 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 100, 2);
			 		start= System.currentTimeMillis();
			 		msort11.Read_and_sort("sortedSublist_t11");
			 		end = System.currentTimeMillis();
			 		times[11]=(end - start);
			 
				 //unorderedFile_3 - 100000 Integers
						M_mway_merge_sort msort12 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 100, 10);
				 		start= System.currentTimeMillis();
				 		msort12.Read_and_sort("sortedSublist_12");
				 		end = System.currentTimeMillis();
				 		times[12]=(end - start);
			 	
				 		
				 	//unorderedFile_3 - 100000 Integers
						M_mway_merge_sort msort13 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 100, 100);
				 		start= System.currentTimeMillis();
				 		msort13.Read_and_sort("sortedSublist_t13");
				 		end = System.currentTimeMillis();
				 		times[13]=(end - start);
				
				//unorderedFile_3 - 100000 Integers
							M_mway_merge_sort msort14 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 100, 1000);
					 		start= System.currentTimeMillis();
					 		msort14.Read_and_sort("sortedSublist_t14");
					 		end = System.currentTimeMillis();
					 		times[14]=(end - start);
					 		
					 ////////////////
					 		
					//unorderedFile_3 - 100000 Integers
							M_mway_merge_sort msort15 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 2);
					 		start= System.currentTimeMillis();
					 		msort15.Read_and_sort("sortedSublist_t15");
					 		end = System.currentTimeMillis();
					 		times[15]=(end - start);
					 
						 //unorderedFile_3 - 100000 Integers
								M_mway_merge_sort msort16 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 10);
						 		start= System.currentTimeMillis();
						 		msort16.Read_and_sort("sortedSublist_t16");
						 		end = System.currentTimeMillis();
						 		times[16]=(end - start);
					 	
						 		
						 	//unorderedFile_3 - 100000 Integers
								M_mway_merge_sort msort17 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 100);
						 		start= System.currentTimeMillis();
						 		msort17.Read_and_sort("sortedSublist_t17");
						 		end = System.currentTimeMillis();
						 		times[17]=(end - start);
						
						//unorderedFile_3 - 100000 Integers
									M_mway_merge_sort msort18 = new M_mway_merge_sort(path, "unorderedFile_3", ".data", 1000, 1000);
							 		start= System.currentTimeMillis();
							 		msort18.Read_and_sort("sortedSublist_t16");
							 		end = System.currentTimeMillis();
							 		times[18]=(end - start); 		
					 		
					 		
					 		
					 		
				/////////////////Input file small enough to fit in memory//////////////////////////7	 		
				//unorderedFile_2 - 10000 Integers
							M_mway_merge_sort msort19 = new M_mway_merge_sort(path, "unorderedFile_2", ".data", 10000, 100);
					 		start= System.currentTimeMillis();
					 		msort19.Read_and_sort("sortedSublist_t17");
					 		end = System.currentTimeMillis();
					 		times[19]=(end - start);
							
				//unorderedFile_2 - 10000 Integers
							M_mway_merge_sort msort20 = new M_mway_merge_sort(path, "unorderedFile_2", ".data", 10000, 100);
					 		start= System.currentTimeMillis();
					 		msort20.Just_sort("JustSort_t18");
					 		end = System.currentTimeMillis();
					 		times[20]=(end - start);
			 	
		 		
		 for(int i=0;i<n_tests;i++){
				System.out.println("Merge Sort running time, test "+ (i+1) + ": "+times[i] + " ms");
		 }
	}
}
