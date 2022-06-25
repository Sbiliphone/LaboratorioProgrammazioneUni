package Laboratorio.Java.Problema14;

import puzzleboard.*;
import java.util.*;
public class Puzzle {
	private String[][] tavola;
	private int dim;
	

	public Puzzle(int n) {
		dim=n;
		tavola= new String[n][n];
		Stack<String> allNum = new Stack<String>();
		for(int i=1; i<=(n*n)-1; i++) {
			allNum.push(""+i);
		}
		Collections.shuffle(allNum);
		
		for(int i=0;i<n;i++) {
			for(int j=0; j<n; j++) {
				if(!allNum.isEmpty()) {
					tavola[i][j]=allNum.pop();
				}else {
					tavola[i][j]="";
				}
			}
		}
	}
	
	public int dim() {
		return dim;
	}
	
	public String tavola(int i, int j) {
		return tavola[i][j];
	}
	
	public boolean sorted() {
		int k=1;
		for(int i=0; i<dim; i++) {
			for(int j=0; j<dim; j++) {
				if(!tavola[i][j].equals(""+(k))) {
					return false;
				}else {
					k++;
				}
			}
		}
		return true;
	}
	
	
	
	public boolean canMove(int i, int j) {
		if (moveTo(i,j)==null) {
			return false;
		}
		return true;
	}
	
	public int[] moveTo(int i, int j) {
		try {
			if(tavola[i+1][j]=="") {
				return new int[] {i+1, j};
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		try {
			if(tavola[i-1][j]=="") {
				return new int[] {i-1, j};
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		try {
			if(tavola[i][j+1]=="") {
				return new int[] {i, j+1};
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		try {
			if(tavola[i][j-1]=="") {
				return new int[] {i, j-1};
			}
		}catch(ArrayIndexOutOfBoundsException e) {
			
		}
		return null;
	}

	
	public void print() {
		for(int i=0; i<dim; i++) {
			for(int j=0; j<dim; j++) {
				System.out.print("|"+tavola[i][j]+"|");
			}
			System.out.println();
		}
	}
	
	public void move(int x, int y, PuzzleBoard gui) {
		if(canMove(x,y)) {
			
			int[] k= moveTo(x,y);
			int i= k[0];
			int j=k[1];
			int value= Integer.parseInt(tavola[x][y]);
			tavola[i][j]=tavola[x][y];
			tavola[x][y]="";
			
			gui.setNumber(i+1, j+1, value);
			gui.clear(x+1, y+1);
			gui.display();
			
		}
	}

	public PuzzleBoard riempi(PuzzleBoard gui) {
		for(int i=0; i<dim; i++) {
			for( int j=0; j<dim; j++) {
				if(tavola[i][j]!="") {
					int k=Integer.parseInt(tavola[i][j]);
					gui.setNumber(i+1,j+1, k);
				}
				
			}
		}
		return gui;
	}
	
	public int[] trova( String k) {
		for(int i=0; i<dim; i++) {
			for (int j=0; j<dim; j++) {
				if(tavola[i][j].equals(k)) {
					return new int[] {i, j};
				}
			}
		}
		return null;
	}
}