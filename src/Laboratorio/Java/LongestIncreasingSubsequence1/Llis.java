package Laboratorio.Java.LongestIncreasingSubsequence1;

public class Llis {
	
	public static void main(String[] args) {
		System.out.println(llis( new int[] {5, 4, 3, 2, 1}));
		System.out.println(llis( new int[] {2, 7, 5, 7, 4}));
		System.out.println(llis( new int[] {47, 38, 39, 25, 44}));
		System.out.println(llis( new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6}));
		System.out.println(llis( new int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61}));
		System.out.println(llis( new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1}));
		System.out.println(llis( new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4}));
		System.out.println(llis( new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5}));
		System.out.println(llis( new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1}));
		System.out.println(llis( new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3}));
		System.out.println(llis( new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5}));
		System.out.println(llis( new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6}));
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