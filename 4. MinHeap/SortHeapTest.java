// A simple sorting test for a (min)heap
//
// Expects an integer argument specifying how many random integers to
// insert into the heap, then loads up the heap, and then removes everything,
// which should result in output of an ordered list of random integers.
// Random int x % 1000, such -1000 < x < 1000
//

import java.util.Random;

public class SortHeapTest{
    public static void main(String [] args){
	Random rg = new Random();
	int count=0;
	int x;
	
	MyMinHeap mmh = new MyMinHeap();
	try{
	    count = Integer.parseInt(args[0]);
	    if(count<0) count *= -1;
	}catch(Exception e){
	    System.err.println("Usage: java SortHeapTest <int>");
	    System.err.println("\t<int> is how many random ints to sort");
	    System.err.println("\t"+e);
	}
	for(int i = count;i>0;i--){
	    x = rg.nextInt() %1000;
	    //	    System.out.println("Add "+x);
	    mmh.add(x);
	}
	//	System.out.println("Removing .... ");
	while(! mmh.isEmpty()){
	    System.out.println(mmh.get());
	    mmh.remove();
	}
    }
}
