//-----------------------------------------------------------------------------
//Albert Garcia
//awgarcia
//pa3
//-----------------------------------------------------------------------------
public class ListTest{
	//Fields
	int data1;
	//Constructor
	ListTest(int d1){
		this.data1=d1;
	}
	//Tostring
	public String toString() {
		return String.valueOf(data1);
	}
	
	
	public static void main(String[] args){
		List A = new List();
		//Generate Objects
		ListTest L1=new ListTest(1);
		ListTest L2=new ListTest(2);
		ListTest L3=new ListTest(3);
		ListTest L4=new ListTest(4);
		ListTest L5=new ListTest(5);
		ListTest L6=new ListTest(6);
		//Append
		System.out.println("Append L1");
		A.append(L1);
		System.out.println(A);
		//Move Front
		A.moveFront();
		System.out.println("InsertBefore L2");
		//Insert Before
		A.insertBefore(L2);
		System.out.println(A);
		//Move Back
		A.moveBack();
		System.out.println("Insert After L3");
		//Insert After
		A.insertAfter(L3);
		System.out.println(A);
		//Move Front
		A.moveFront();
		System.out.println("Move cursor forward 1");
		//Move Next
		A.moveNext();
		System.out.println("Print index and data");
		//Print Index
		System.out.println("index: "+A.index());
		//Print Data
		System.out.println("data: "+A.get());
		System.out.println("Move cursor forward 1 and back 1");
		//Move Next
		A.moveNext();
		//MOve Previous
		A.movePrev();
		//Print Index
		System.out.println("index: "+A.index());
		//Print Data
		System.out.println("data: "+A.get());
		System.out.println("Delete that element");
		//Delete
		A.delete();
		System.out.println(A);
		System.out.println("Delete Front");
		//Delete Front
		A.deleteFront();
		System.out.println(A);
		System.out.println("Delete Back");
		//Delete Back
		A.deleteBack();
		System.out.println(A);
		System.out.println("Prepend L4");
		//Prepend
		A.prepend(L4);
		System.out.println(A);
		System.out.println("Append L5");
		//Append
		A.append(L5);
		System.out.println(A);
		System.out.println("Prepend L6");
		//Prepend
		A.prepend(L6);
		System.out.println(A);
		System.out.println("Clear list");
		//Clear
		A.clear();
		System.out.println("Print out Length");
		//Check Length
		System.out.println(A.length());
	}

}
