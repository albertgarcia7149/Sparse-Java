//-----------------------------------------------------------------------------
//Albert Garcia
//awgarcia
//pa3
//-----------------------------------------------------------------------------
class Matrix{
	private class Entry{
		//FIELDS
		int column;
		double data;
		
		//CONSTRUCTOR
		Entry(int column,double data){
			this.column=column;
			this.data=data;
		}
		
		//GETTERS
		double getData(){
			return this.data;
		}
		int getColumn(){
			return this.column;
		}
		//TOSTRING
		public String toString(){
			return "("+ String.valueOf(column+1)+", "+String.valueOf(data)+")";
		}
		public boolean equals(Object x){
			boolean eq=false;
			Entry that;
			if(x instanceof Entry){
				that= (Entry) x;
				eq=((this.data==that.getData())&&(this.column==that.getColumn())); 
			}
			return eq;
		}
	}
	
	//FIELDS----------------------------------------------------------------------
	List[] m;
	int size;
	
	//CONSTRUCTOR------------------------------------------------------------------
	Matrix(int n){
		if(n<0){
			throw new RuntimeException("Matrix Error: Size must be > 0");
		}else{
				size=n;
				m=new List[size];
				for(int i=0;i<size;i++){
					m[i]=new List();
				}
		}
	}
	
	//HELPER FUNCTIONS
	
	List getRow(int i){ 
		return m[i];
	}
	
	double dotProduct(List T, List m){
		double sum=0;
		if(T.length()==0 || m.length()==0){
			//sum stays at 0
		}else{
			T.moveFront();
			m.moveFront();
			while(T.index()!=-1 && m.index()!=-1){
				Entry ET;
				Entry Em;
				ET=(Entry)T.get();
				Em=(Entry)m.get();
				if(ET.getColumn()==Em.getColumn()){
					sum+=ET.getData()*Em.getData();
					T.moveNext();
					m.moveNext();
				} else if(ET.getColumn()<Em.getColumn()){
					T.moveNext();
				}else{
					m.moveNext();
				}
			}
		}
		return sum;
	}
	
	Matrix addhelp(Matrix temp,List A,List B,int row,int op){
		//A+B
		A.moveFront();
		B.moveFront();
		Entry EA;
		Entry EB;
		while(A.index()!=-1 && B.index()!=-1){
			EA=(Entry)A.get();
			EB=(Entry)B.get();
			if(EA.getColumn()==EB.getColumn()){
				//col the same
				temp.changeEntry(row+1,EA.getColumn()+1,EA.getData()+EB.getData()*op); 
				A.moveNext();
				B.moveNext();
			}else if(EA.getColumn()<EB.getColumn()){
				temp.changeEntry(row+1,EA.getColumn()+1,EA.getData());
				A.moveNext();
			}else{
				temp.changeEntry(row+1,EB.getColumn()+1,EB.getData()*op);
				B.moveNext();
			}
		}
		while(A.index()!=-1){
			EA=(Entry)A.get();
			temp.changeEntry(row+1,EA.getColumn()+1,EA.getData());
			A.moveNext();
		}
		while(B.index()!=-1){
			EB=(Entry)B.get();
			temp.changeEntry(row+1,EB.getColumn()+1,EB.getData()*op);
			B.moveNext();
		}
		return temp;
	}
	
	//ACCESS FUNCTIONS-----------------------------------------------------------------
	int getSize(){return size;}
	int getNNZ(){
		int count=0;
		for(int i=0;i<size;i++){
			count+=m[i].length();
		}
		return count;
	}
	public boolean equals(Object x){
		boolean eq =false;
		Matrix M;
		
		if(x instanceof Matrix){
			M=(Matrix)x;
			eq=(size==M.getSize());
			for(int i=0;i<size && eq;i++){
				eq=m[i].equals(M.getRow(i));
			}
		}
		return eq;
	}
	//MANIPULATION PROCEDURES-----------------------------------------------------
	void makeZero(){
		for(int i=0;i<size;i++){
			m[i].clear();
		}
	}
	Matrix copy(){
		Matrix M=new Matrix(size);
		for(int i=0; i<size;i++){
			if(m[i].length()>0){
				m[i].moveFront();
				while(m[i].index()!=-1){
					Entry temp;
					temp=(Entry)m[i].get();
					M.changeEntry(i+1,temp.getColumn()+1,temp.getData());
					m[i].moveNext();
				}
			}
		}
		return M;
	}
	
	void changeEntry(int i,int j,double x){
		//so they fit standard array index
		i--;
		j--;
		//check if entry is in range
		if( ((i>-1) && (i<size))  && ((j>-1) && (j<size)) ){
			boolean notfound=true;
			Entry E= new Entry(j,x);
			if(m[i].length()==0 && x!=0){
				m[i].append(E);
			}else{
				m[i].moveFront();
				while(m[i].index()!=-1 && notfound){
					Entry temp;
					temp=(Entry)m[i].get();
					if(temp.getColumn()==E.getColumn()){
						if(x!=0){
							m[i].insertBefore(E);
						}
						m[i].delete();
						notfound=false;
					}else if(temp.getColumn()>E.getColumn()&& x!=0){
						m[i].insertBefore(E);
						notfound=false;
					}else{
						m[i].moveNext();
					}
				}
				if(notfound && x!=0){
					m[i].append(E);
				}
			}
		}
	}
	Matrix transpose(){
		Matrix M=new Matrix(size);
		for(int i=0;i<size;i++){
			if(m[i].length()>0){
				m[i].moveFront();
				while(m[i].index()!=-1){
					Entry temp;
					temp=(Entry)m[i].get();
					M.changeEntry(temp.getColumn()+1,i+1,temp.getData());
					m[i].moveNext();
				}
			}
		}
		return M;
	}
	Matrix scalarMult(double x){
		Matrix M=new Matrix(size);
		for(int i=0; i<size;i++){
			if(m[i].length()>0){
				m[i].moveFront();
				while(m[i].index()!=-1){
					Entry temp;
					temp=(Entry)m[i].get();
					M.changeEntry(i+1,temp.getColumn()+1,temp.getData()*x);
					m[i].moveNext();
				}
			}
		}
		return M;
	}
	Matrix mult(Matrix M){
		if(size!=M.getSize()){
			throw new RuntimeException("Matrix Mult Error: Sizes must be the same");
		}else{
			Matrix temp= new Matrix(size);
			Matrix T=M.transpose();
			for(int i=0;i<size;i++){
				for(int j=0;j<size;j++){
					temp.changeEntry(i+1,j+1,dotProduct(T.getRow(i),m[j]));
				}
			}
			return temp.transpose();
		}
	}
	Matrix add(Matrix M){//op=1 for addition op=-1 for subtraction
		int op=1;
		if(size==M.getSize()){
			Matrix temp=new Matrix(size);
			Matrix N=this.copy();
			for(int i=0;i<size;i++){
				temp=addhelp(temp,N.getRow(i),M.getRow(i),i,op);
			}
			return temp;
		}else{
			throw new RuntimeException("Matrix Add Error: Sizes must be the same");
		}
	}
	Matrix sub(Matrix M){//op=1 for addition op=-1 for subtraction
		int op=-1;
		if(size==M.getSize()){
			Matrix temp=new Matrix(size);
			Matrix N=this.copy();
			for(int i=0;i<size;i++){
				temp=addhelp(temp,N.getRow(i),M.getRow(i),i,op);
			}
			return temp;
		}else{
			throw new RuntimeException("Matrix Add Error: Sizes must be the same");
		}
	}
	
	//OTHER FUNCTIONS----------------------------------------------------------------------------
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<getSize();i++){
			if(m[i].length()>0){
				if(i!=0){
					sb.append("\n");
				}
				sb.append((i+1)+": ");
				sb.append(m[i].toString());
			}
		}
		if(getNNZ()>0){
			sb.append("\n");
		}
		return new String(sb);
	}
	
	
	
	
	
	
	
	
}