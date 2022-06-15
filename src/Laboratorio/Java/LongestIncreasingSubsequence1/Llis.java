package Laboratorio.Java.LongestIncreasingSubsequence1;

public class Llis {
	
	public static void main(String[] args) {
		System.out.println(llis( new int[] {5, 4, 3, 2, 1}));
		
	}

	public static int llis(int[] s){ // s[i] > 0 per i in [0,n-1], dove n = s.length
	 	return llisRec(s,0,0);
	}

	 private static int llisRec(int[] s, int i, int t){
		 if (i == s.length) { // i = n : coda di s vuota
			 return 0;
		 }else if (s[i] <= t) { // x = s[i] <= t : x non pu� essere scelto
			 return llisRec(s, i+1, t);
		 }else { // x > t : x pu� essere scelto o meno
			 return Math.max(1 + llisRec(s,i+1,s[i]), llisRec(s,i+1,t));
		 }
	 }
}