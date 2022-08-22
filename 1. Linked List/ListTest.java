
public class ListTest{

    public static void main(String [] args){
	SList mylist = new SList();

	System.out.println("\n\t --Empty--");
	printStatus(mylist);

	System.out.println("\n\t --Add one--");
	mylist.add("one");
	printStatus(mylist);

	System.out.println("\n\t --Add two--");
    	mylist.add("two");
	printStatus(mylist);
	
	System.out.println("\n\t --Add three--");
    	mylist.add("three");
	printStatus(mylist);

	System.out.println("\n\t --Test membership--");
	testMember(mylist, "two");
	testMember(mylist, "one");
	testMember(mylist, "four");
	testMember(mylist, "three");

	System.out.println("\n\t --Remove four--");
	mylist.remove("four");
	printStatus(mylist);

	System.out.println("\n\t --Remove three--");
	mylist.remove("three");
	printStatus(mylist);
	testMember(mylist, "three");

	System.out.println("\n\t --Add four--");
	mylist.add("four");
	printStatus(mylist);
	testMember(mylist,"four");
	
	System.out.println("\n\t --Remove two--");
	mylist.remove("two");
	printStatus(mylist);

	System.out.println("\n\t --Add five--");
	mylist.add("five");
	printStatus(mylist);
	
	System.out.println("\n\t --Remove one--");
	mylist.remove("one");
	printStatus(mylist);


	System.out.println("\n\t --Remove five--");
	mylist.remove("five");
	printStatus(mylist);

	System.out.println("\n\t --Remove four--");
	mylist.remove("four");
	printStatus(mylist);

	System.out.println("\n\t --Remove three--");
	mylist.remove("three");
	printStatus(mylist);
	testMember(mylist, "three");
    }

    private static void printStatus(SList sl){
	sl.dump();
	System.out.println("\t * length: " + sl.length() + "\n\t * empty: "+sl.isEmpty());
    }

    private static void testMember(SList sl, String w){
	System.out.print("\t * ");
    	if(sl.has(w)) System.out.println("Has "+w);else System.out.println("Missing "+w);
    }
}

/*  Output:

	 --Empty--
	 * length: 0
	 * empty: true

	 --Add one--
one
	 * length: 1
	 * empty: false

	 --Add two--
two
one
	 * length: 2
	 * empty: false

	 --Add three--
three
two
one
	 * length: 3
	 * empty: false

	 --Test membership--
	 * Has two
	 * Has one
	 * Missing four
	 * Has three

	 --Remove four--
three
two
one
	 * length: 3
	 * empty: false

	 --Remove three--
two
one
	 * length: 2
	 * empty: false
	 * Missing three

	 --Add four--
four
two
one
	 * length: 3
	 * empty: false
	 * Has four

	 --Remove two--
four
one
	 * length: 2
	 * empty: false

	 --Add five--
five
four
one
	 * length: 3
	 * empty: false

	 --Remove one--
five
four
	 * length: 2
	 * empty: false

	 --Remove five--
four
	 * length: 1
	 * empty: false

	 --Remove four--
	 * length: 0
	 * empty: true

	 --Remove three--
	 * length: 0
	 * empty: true
	 * Missing three
 */
