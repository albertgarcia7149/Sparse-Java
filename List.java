//-----------------------------------------------------------------------------
//Albert Garcia
//awgarcia
//pa3
//-----------------------------------------------------------------------------
public class List {
	//node class
	private class Node{
		Object data;
		Node next;
		Node prev;
		//Constructor
		Node(Object data){
			this.data=data;
			next=null;
			prev=null;
		}
		//override toString method
		public String toString() {
			return String.valueOf(data);
		}
		//overrides equal method
		public boolean equals(Object x) {
			boolean eq=false;
			Node that;
			if(x instanceof Node) {
				that=(Node) x;
				eq=(this.data.equals(that.data));
			}
			return eq;
		}
	}
	
	//List Variables
	private int index;
	private int length;
	private Node head;
	private Node tail;
	private Node cursor;
	//Constructor
	public List(){
		head=null;
		tail=null;
		cursor=null;
		index=-1;
		length=0;
	}
	//ACCESS FUNCTIONS
	int length() {
		return length;
	}
	int index() {
		return index;
	}
	Object front() {
		if (length<1) {
			throw new RuntimeException("List Error: front() called on empty List");
		}else {
			return head.data;
		}
	}
	Object back() {
		if(length<1) {
			throw new RuntimeException("List Error: back() called on empty List");
		}else {
			return tail.data;
		}
	}
	Object get() {
		if(length<1 || index<0) {
			throw new RuntimeException("List Error: get() called on empty List or undefined cursor");
		}else {
			return cursor.data;
		}
	}
	
	//MANIPULATION PROCEDURES
	void clear() {
		while(head!=null) {
			moveFront();
			delete();
		}
	}
	void moveFront() {
		if(length>0) {
			cursor=head;
			index=0;
		}
	}
	void moveBack() {
		if(length>0) {
			cursor=tail;
			index=length-1;
		}
	}
	void movePrev() {
		if(cursor!=null) {
			if(cursor!=head) {
				cursor=cursor.prev;
				index--;
			}else {
				cursor=null;
				index=-1;
			}
		}
	}
	void moveNext() {
		if(cursor!=null) {
			if(cursor!=tail) {
				cursor=cursor.next;
				index++;
			}else {
				cursor=null;
				index=-1;
			}
		}
	}
	void prepend(Object d) {
		Node temp = new Node(d);
		if(head==null) {
			head=temp;
			tail=temp;
		}else {
			temp.next=head;
			head.prev=temp;
			head=temp;
		}
		length++;
		index++;
	}
	void append(Object d) {
		Node temp=new Node(d);
		if(tail==null) {
			head=temp;
			tail=temp;
		}else {
			temp.prev=tail;
			tail.next=temp;
			tail=temp;
		}
		length++;
	}
	void insertBefore(Object d) {
		if(length>0 && index>-1) {
			if(length==1 || index==0) {
				prepend(d);
			}else {
				Node a=cursor.prev;
				Node temp=new Node(d);
				temp.next=cursor;
				temp.prev=a;
				a.next=temp;
				cursor.prev=temp;
				a=null;
				index++;
				length++;
			}
		}
	}
	void insertAfter(Object d) {
		if(length>0 && index>-1) {
			if (length==1 || index==(length-1) ) {
				append(d);
			}else {
				Node a = cursor.next;
				Node temp=new Node(d);
				temp.next=a;
				temp.prev=cursor;
				cursor.next=temp;
				a.prev=temp;
				a=null;
				length++;
			}
		}
	}
	void deleteFront() {
		if(length>0) {
			if(length>1) {
				if(cursor==head) {
					cursor=null;
				}
				head=head.next;
				head.prev=null;
				index--;
			}else {
				head=null;
				tail=null;
				index=-1;
			}
			length--;
		}
	}
	void deleteBack() {
		if(length>0) {
			if(length>1) {
				if(cursor==tail) {
					cursor=null;
					index=-1;
				}
				tail=tail.prev;
				tail.next=null;
			}else {
				head=null;
				tail=null;
				index=-1;
			}
			length--;
		}
	}
	void delete() {
		if(length>0 && index>-1) {
			if(length==1) {
				head=null;
				tail=null;
				cursor=null;
				index=-1;
				length--;
			}else if(cursor==head) {
				deleteFront();
				cursor=null;
				index=-1;
			}else if(cursor==tail) {
				deleteBack();
				cursor=null;
				index=-1;
			}else {
				Node l = cursor.prev;
				Node r = cursor.next;
				l.next=r;
				r.prev=l;
				cursor.next=null;
				cursor.prev=null;
				cursor=null;
				index=-1;
				length--;
			}
		}
	}
	//OTHER METHODS
	public boolean equals(Object x) {
		boolean eq=false;
		List L;
		Node N,M;
		if(x instanceof List) {
			L=(List)x;
			N=this.head;
			M=L.head;
			eq=(this.length==L.length());
			while(eq&& N!=null) {
				eq=N.equals(M);
				N=N.next;
				M=M.next;
			}
		}
		return eq;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node temp=head;
		while(temp!=null) {
			sb.append(temp.toString());
			if(temp.next!=null){
				sb.append(" ");
			}
			temp=temp.next;
		}
		return new String(sb);
	}
	List copy() {
		List l = new List();
		Node n = head;
		while(n!=null) {
			l.append(n.data);
			n=n.next;
		}
		index=-1;
		cursor=null;
		l.length=this.length;
		return l;
	}
}
