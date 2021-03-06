package Laboratorio.Java.LongestIncreasingSubsequence1;

public class LlisSem {
	
	public static void main(String[] args) {
		System.out.println(llisSemM(new int[] {5, 4, 3, 2, 1})); // 1
		System.out.println(llisSemM(new int[] {2, 7, 5, 7, 4})); // 3
		//System.out.println(llisSemM(new int[] {47, 38, 39, 25, 44})); // 3
		//System.out.println(llisSemM(new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6})); // 4
		//System.out.println(llisSemM(new int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61})); // 5
		//System.out.println(llisSemM(new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1})); // 3
		System.out.println(llisSemM(new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4})); // 4
		System.out.println(llisSemM(new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5})); // 5
		System.out.println(llisSemM(new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1})); // 4
		System.out.println(llisSemM(new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3})); // 5
		System.out.println(llisSemM(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5})); // 5
		System.out.println(llisSemM(new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6})); // 6

	}
	
	private static final int UNKNOWN = -1;
    
	// con vettore
    public static int llisSem(int[] s) {
        int[] mem = new int[s.length+1];
    	// inizializzo tutto il vettore mem a -1
    	for(int i = 0; i < mem.length; i++) {
    		mem[i] = UNKNOWN;
    	}
        return llisRec(s, 0, 0, mem);
    }


    private static int llisRec(int[] s, int i, int t, int[] mem) {
    	if (mem[t] == UNKNOWN) {
	        if (i == s.length){
	            mem[t] = 0;
	        }
	        else if (s[i] <= t) {
	            mem[t] = llisRec(s, i + 1, t, mem);
	        } else {
	            mem[t] = Math.max( 1+llisRec(s,i+1,s[i],mem), llisRec(s,i+1,t,mem));
	        }
    	}
        return mem[t];
    }
    
    // con matrice
    public static int llisSemM(int[] s) {
        int[][] mem = new int[s.length+1][s.length+1];
    	// inizializzo tutto il vettore mem a -1
    	for(int i = 0; i < mem.length; i++) {
    		for(int j = 0; j < mem.length; j++) {
    			mem[i][j] = UNKNOWN;
    		}
    		
    	}
        return llisRecM(s, 0, 0, mem);
    }


    private static int llisRecM(int[] s, int i, int t, int[][] mem) {
    	if (mem[i][t] == UNKNOWN) {
	        if (i == s.length){
	            mem[i][t] = 0;
	        }
	        else if (s[i] <= t) {
	            mem[i][t] = llisRecM(s, i + 1, t, mem);
	        } else {
	            mem[i][t] = Math.max( 1+llisRecM(s,i+1,s[i],mem), llisRecM(s,i+1,t,mem));
	        }
    	}
        return mem[i][t];
    }
}
