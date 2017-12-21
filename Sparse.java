//-----------------------------------------------------------------------------
//Albert Garcia
//awgarcia
//pa3
//-----------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Sparse{
	public static void main(String[] args) throws IOException{
		Scanner in =null;
		PrintWriter out =null;
		String line= null;
		String[] token =null;
		
		if(args.length!=2){
			System.err.println("Usage: FileIO infile outfile");
			System.exit(1);
		}
		
		in=new Scanner(new File(args[0]));
		out =new PrintWriter(new FileWriter(args[1]));
		//get first line of file
		line=in.nextLine()+" ";
		token=line.split("\\s+");
		//makes the 2 empty matrices
		Matrix A=new Matrix(Integer.parseInt(token[0]));
		Matrix B=new Matrix(Integer.parseInt(token[0]));
		//loop counters
		int a=Integer.parseInt(token[1]);
		int b=Integer.parseInt(token[2]);
		//empty line
		line=in.nextLine();
		//fill Matrix A
		for (int i=0; i<a;i++){
			line=in.nextLine()+" ";
			token=line.split("\\s+");
			A.changeEntry(Integer.parseInt(token[0]),Integer.parseInt(token[1]),Double.parseDouble(token[2]));
		}
		//empty line
		line=in.nextLine();
		//fill Matrix B
		for(int i=0;i<b;i++){
			line=in.nextLine()+" ";
			token=line.split("\\s+");
			B.changeEntry(Integer.parseInt(token[0]),Integer.parseInt(token[1]),Double.parseDouble(token[2]));
		}
		//close infile
		in.close();
		//Matrices NNZ
		out.println("A has "+A.getNNZ()+ " non-zero entries:");
		out.println(A);
		out.println("B has "+B.getNNZ()+ " non-zero entries:");
		out.println(B);
		//A scalarMult (1.5)
		out.println("(1.5)*A =");
		out.println(A.scalarMult(1.5));
		//A+B
		out.println("A+B =");
		out.println(A.add(B));
		//A+A
		out.println("A+A =");
		out.println(A.add(A));
		//B-A
		out.println("B-A =");
		out.println(B.sub(A));
		//A-A
		out.println("A-A =");
		out.println(A.sub(A));
		//Transpose A
		out.println("Transpose(A) =");
		out.println(A.transpose());
		//A*B
		out.println("A*B =");
		out.println(A.mult(B));
		//B*B
		out.println("B*B =");
		out.println(B.mult(B));
		
		out.close();
	
	}	
}