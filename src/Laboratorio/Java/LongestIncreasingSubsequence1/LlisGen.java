package Laboratorio.Java.LongestIncreasingSubsequence1;
public class LlisGen {
	
	public static void main(String[] args) {
		System.out.println(llisGenM(new int[] {5, 4, 3, 2, 1})); // 1
		System.out.println(llisGenM(new int[] {2, 7, 5, 7, 4})); // 3
		System.out.println(llisGenM(new int[] {47, 38, 39, 25, 44})); // 3
		System.out.println(llisGenM(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6})); // 4
		System.out.println(llisGenM(new int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61})); // 5
		System.out.println(llisGenM(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1})); // 3
		System.out.println(llisGenM(new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4})); // 4
		System.out.println(llisGenM(new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5})); // 5
		System.out.println(llisGenM(new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1})); // 4
		System.out.println(llisGenM(new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3})); // 5
		System.out.println(llisGenM(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5})); // 5
		System.out.println(llisGenM(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6})); // 6
	}
	
	private static final int UNKNOWN = -1;

	
	// con vettore
	public static int llisGen(int[] s) { // s[i] > 0 per i in [0,n-1], dove n = s.length
	    int[] mem = new int[s.length+1];
	    // inizializzo tutto il vettore mem a -1
		for(int i = 0; i < mem.length; i++) {
			mem[i] = UNKNOWN;
		}
	    return llisRec(s, 0,s.length, mem);
	}
	

	private static int llisRec(int[] s, int i, int j, int[] mem) {
		// dalle condizioni descritte dal professore inizializzo una variabile t
		// assegno il valore a t sulla base dell'interpretazione 
        // t = s[j] se 0 <= j < n oppure t = 0 se j = n
		int t = ( j == s.length ) ? 0 : s[j];
		if (mem[j] == UNKNOWN) {
			if (i == s.length) {
				mem[j] = 0;
			}else if (s[i] <= t) {
				mem[j] = llisRec(s, i + 1, j, mem);
			} else {
				mem[j] = Math.max(1 + llisRec(s, i + 1, i, mem), llisRec(s, i + 1, j, mem));
			}
		}
		return mem[j];
	}
	
	// con matrice
	public static int llisGenM(int[] s) { // s[i] > 0 per i in [0,n-1], dove n = s.length
	    int[][] mem = new int[s.length+1][s.length+1];
	    // inizializzo tutto il vettore mem a -1
		for(int i = 0; i < mem.length; i++) {
			for(int j = 0; j < mem.length; j++) {
				mem[i][j] = UNKNOWN;
			}
			
		}
	    return llisRecM(s, 0,s.length, mem);
	}
	
	
	private static int llisRecM(int[] s, int i, int j, int[][] mem) {
		// dalle condizioni descritte dal professore inizializzo una variabile t
		// assegno il valore a t sulla base dell'interpretazione 
        // t = s[j] se 0 <= j < n oppure t = 0 se j = n
		int t = ( j == s.length ) ? 0 : s[j];
		if (mem[i][j] == UNKNOWN) {
			if (i == s.length) {
				mem[i][j] = 0;
			}else if (s[i] <= t) {
				mem[i][j] = llisRecM(s, i + 1, j, mem);
			} else {
				mem[i][j] = Math.max(1 + llisRecM(s, i + 1, i, mem), llisRecM(s, i + 1, j, mem));
			}
		}
		return mem[i][j];
	}

}