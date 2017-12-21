//-----------------------------------------------------------------------------
//Albert Garcia
//awgarcia
//pa3
//-----------------------------------------------------------------------------
public class MatrixTest{
	public static void main(String[] args){
	Matrix A = new Matrix(10);
	Matrix B = new Matrix(10);
	A.changeEntry(1,1,1);
	A.changeEntry(1,2,2);
	A.changeEntry(1,3,3);
	A.changeEntry(2,1,4);
	A.changeEntry(2,2,5);
	A.changeEntry(2,3,6);
	A.changeEntry(3,1,7);
	A.changeEntry(3,2,8);
	A.changeEntry(3,3,9);
	System.out.println("Matrix A:");
	System.out.println(A.toString());
	B.changeEntry(1,1,1);
	B.changeEntry(1,2,0);
	B.changeEntry(1,3,1);
	B.changeEntry(2,1,0);
	B.changeEntry(2,2,0);
	B.changeEntry(2,3,0);
	B.changeEntry(3,1,1);
	B.changeEntry(3,2,1);
	B.changeEntry(3,3,1);
	System.out.println("Matrix B:");
	System.out.println(B);
	
	//A=B Test
	System.out.println(A.equals(B));
	//Copy Test
	Matrix C = A.copy();
	System.out.println("Matrix C a copy of A:");
	System.out.println(C.toString());
	//Make Zero Test
	C.makeZero();
	System.out.println("Matrix C after Make Zero:");
	System.out.println(C.toString());
	//NZZ Test
	System.out.println("Matrix A NNZ:"+ A.getNNZ());
	System.out.println("Matrix B NNZ:"+ B.getNNZ());
	System.out.println("Matrix C NNZ:"+ C.getNNZ());
	//Transpose Test
	Matrix D= A.transpose();
	System.out.println("Matrix D Transpose of A:");
	System.out.println(D.toString());
	//Scalar Mult Test
	Matrix Z=A.scalarMult(-2);
	System.out.println("Matrix Z Scalar Mult -2:");
	System.out.println(Z.toString());
	//Mult Test
	Matrix E= B.mult(B);
	System.out.println("Matrix E  Mult B*B:");
	System.out.println(E.toString());
	//Add Test	
	Matrix F = B.add(B);
	System.out.println("Matrix F  Add B+B:");
	System.out.println(F.toString());
	Matrix G = B.add(A);
	System.out.println("Matrix G  Add B+A:");
	System.out.println(G.toString());
	//Sub Test
	Matrix H= B.sub(B);
	System.out.println("Matrix H  Sub B-B:");
	System.out.println(H.toString());
	Matrix I= B.sub(A);
	System.out.println("Matrix I  Sub B-A:");
	System.out.println(I.toString());
	
	}

}