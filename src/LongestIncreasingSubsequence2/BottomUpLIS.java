package LongestIncreasingSubsequence2;

import java.util.Arrays;

public class BottomUpLIS {
	
	public static void main(String[] args) {
		// esempi
		int[] uno = new int[] {5, 4, 3, 2, 1};
		System.out.println(llisDP(uno)); // 1
		System.out.println(Arrays.toString(lisDP(uno)));
		
		int[] due = new int[] {2, 7, 5, 7, 4};
		System.out.println(llisDP(due)); // 3
		System.out.println(Arrays.toString(lisDP(due)));
		
		int[] tre = new int[] {47, 38, 39, 25, 44};
		System.out.println(llisDP(tre)); // 3
		System.out.println(Arrays.toString(lisDP(tre)));
		
		int[] quattro = new int[] {27, 90, 7, 29, 49, 8, 53, 1, 28, 6};
		System.out.println(llisDP(quattro)); // 4
		System.out.println(Arrays.toString(lisDP(quattro)));
		
		int[] cinque = new int[] {9, 46, 54, 71, 60, 47, 1, 32, 25, 61};
		System.out.println(llisDP(cinque)); // 5
		System.out.println(Arrays.toString(lisDP(cinque)));
		
		int[] sei = new int[] {54, 52, 42, 33, 14, 40, 37, 61, 53, 1};
		System.out.println(llisDP(sei)); // 3
		System.out.println(Arrays.toString(lisDP(sei)));

		int[] sette = new int[] {10, 8, 9, 5, 6, 7, 1, 2, 3, 4};
		System.out.println(llisDP(sette)); // 4
		System.out.println(Arrays.toString(lisDP(sette)));
		
		int[] otto = new int[] {10, 11, 12, 6, 7, 8, 9, 1, 2, 3, 4, 5};
		System.out.println(llisDP(otto)); // 5
		System.out.println(Arrays.toString(lisDP(otto)));
		
		int[] nove = new int[] {7, 8, 9, 10, 4, 5, 6, 2, 3, 1};
		System.out.println(llisDP(nove)); // 4
		System.out.println(Arrays.toString(lisDP(nove)));
		
		int[] dieci = new int[] {8, 9, 10, 11, 12, 4, 5, 6, 7, 1, 2, 3};
		System.out.println(llisDP(dieci)); // 5
		System.out.println(Arrays.toString(lisDP(dieci)));
		
		int[] undici = new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5};
		System.out.println(llisDP(undici)); // 5
		System.out.println(Arrays.toString(lisDP(undici)));
		
		int[] dodici = new int[] {6, 1, 7, 2, 8, 3, 9, 4, 10, 5, 6};
		System.out.println(llisDP(dodici)); // 6
		System.out.println(Arrays.toString(lisDP(dodici)));
		

	}

	   // Length of Longest Increasing Subsequence (LLIS):
	   // Programmazione dinamica bottom-up

	public static int llisDP(int[] s) {

		int n = s.length;

		int[][] mem = new int[n + 1][n + 1];

	    // Matrice: valori delle ricorsioni di llisRec
	    // relativi a diversi valori degli argomenti
	
	    for (int j = 0; j <= n; j++) {
	
	         // --------------------------------------------------
	         //  Inserisci qui i comandi per registrare i valori
	         //  corrispondenti ai casi base della ricorsione
	         // --------------------------------------------------
	    	mem [n][j] = 0; // casi base
	      }

	  // i indice di colonna, j indice di riga
      for (int i = n - 1; i >= 0; i--) {
         for (int j = 0; j <= n; j++) {
            // ------------------------------------------------
            //  Inserisci qui le strutture di controllo
            //  appropriate e i comandi per registrare
            //  i valori corrispondenti ai casi ricorsivi
            // ------------------------------------------------
        	 int t = 0; // creo la variabile locale t
	          
	          // assegno il valore a t sulla base dell'interpretazione 
	          // t = s[j] se 0 <= j < n oppure t = 0 se j = n  
	          if ((0 <= j) && (j < n)) {
	              t = s[j];
	          } else if (j == n) {
	              t = 0;
	          }
	          
	          if (s[i] <= t) {
	              mem[i][j] = mem[i+1][j];
	          } else {
	              mem[i][j] = Math.max(1 + mem [i+1][i], mem [i+1][j]);
	          }
         }
      }

      // ----------------------------------------------------
      //  Inserisci di seguito l'elemento della matrice
      //  il cui valore corrisponde a llis(s) :

      return mem[0][n];

      // ----------------------------------------------------
   }

   // Longest Increasing Subsequence (LIS):
   // Programmazione dinamica bottom-up

   public static int[] lisDP(int[] s) {

      int n = s.length;

      int[][] mem = new int[n + 1][n + 1];

      // 1. Matrice: valori delle ricorsioni di llisRec
      //    calcolati esattamente come per llisDP
      for (int j = 0; j <= n; j++) {
    	  mem [n][j] = 0; // casi base
	  }

      for (int i = n - 1; i >= 0; i--) {
    	  for (int j = 0; j <= n; j++) {
	     	 int t = 0; // creo la variabile locale t 
	          // assegno il valore a t sulla base dell'interpretazione 
	          // t = s[j] se 0 <= j < n oppure t = 0 se j = n          
	          if ( (0 <= j) && (j < n)) {
	              t = s[j];
	          } else if (j == n) {
	              t = 0;
	          }
	          if (s[i] <= t) {
	              mem[i][j] = mem[i+1][j];
	          } else {
	              mem[i][j] = Math.max(1 + mem [i+1][i], mem [i+1][j] );
	          }
	      }
      }
      

      // 2. Cammino attraverso la matrice per ricostruire
      //    un esempio di Longest Increasing Subsequence

      // ----------------------------------------------------
      //  Inserisci di seguito l'elemento della matrice
      //  il cui valore corrisponde a llis(s) :

      int m = mem[0][n];

      // ----------------------------------------------------

      int[] r = new int[m]; // per rappresentare una possibile LIS

      // ----------------------------------------------------
      //  Introduci e inizializza qui gli indici utili
      //  per seguire un cammino attraverso la matrice e
      //  per assegnare gli elementi della sottosequenza r
      // ----------------------------------------------------
      int i = 0; // da colonna 0
	  int j = n; // da riga n
      int k = 0; // indice per spostarmi in r

      while (mem[i][j] > 0) {
         int t = (j == n) ? 0 : s[j];
         // --------------------------------------------------
         //  Inserisci qui strutture di controllo e comandi
         //  per scegliere e seguire un percorso appropriato
         //  attraverso la matrice in modo da ricostruire in
         //  r una possibile LIS relativa alla sequenza s
         // --------------------------------------------------
         if ((mem[i+1][i] + 1 > mem[i+1][j])) {
        	// per ricostruire il percorso inverso so devo controllare se s[i] >= t
	          if(s[i]>=t){
	              r[k] = s[i];   //inserisco valore nell'array
	              k++;           //aumento indice array
	              j = i;         //cambio indice riga
	              i++;           //aumento la colonna
	          } else {
	              i++;           //aumento la colonna
	          }
	      } else {
	          i++;               //aumento la colonna
	      }
      }

      return r; // = LIS relativa alla sequenza s
   }

} // class BottomUpLIS