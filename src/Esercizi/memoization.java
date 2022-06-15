package Esercizi;


/*
 * Approccio imperativo: Tecniche di memoization e programmazione dinamica
 *
 * Ultimo aggiornamento: 29/04/2021
 */


import java.math.*;  // BigInteger


public class memoization {


/* Numeri di Fibonacci (tree recursion)

  (define fibonacci
    (lambda (n)      ; n naturale
      (if (< n 2)
          1
          (+ (fibonacci (- n 2)) (fibonacci (- n 1)))
          )
      ))
*/

// Traduzione in Java:

    public static long fibonacci( int n ) {  // n >= 0

        if ( n < 2 ) {
            return  1;
        } else {
            return  fibonacci( n-2 ) + fibonacci( n-1 );
        }
    }


// Nuova versione che applica la tecnica di memoization:

    public static long fibonacciMem( int n ) {  // n >= 0

        long[] mem = new long[ n+1 ];

        for ( int i=0; i<=n; i=i+1 ) {
            mem[i] = UNKNOWN;
        }
        return fibonacciRec( n, mem );
    }

    private static long fibonacciRec( int n, long[] mem ) {

        if ( mem[n] == UNKNOWN ) {

            if ( n < 2 ) {
                mem[n] = 1;
            } else {
                mem[n] = fibonacciRec( n-2, mem ) + fibonacciRec( n-1, mem );
            }}
        return mem[n];
    }

    private static final long UNKNOWN = 0;


// Superamento dei limiti delle rappresentazioni numeriche a parola fissa:
// int:         risultato rappresentabile per n <= 45
// long:        risultato rappresentabile per n <= 91
// BigInteger:  rappresentazione intera equiparabile a quella di Scheme

    public static BigInteger fibonacciBig( int n ) {  // n >= 0

        BigInteger[] mem = new BigInteger[ n+1 ];

        for ( int i=0; i<=n; i=i+1 ) {
            mem[i] = BIG_UNKNOWN;
        }
        return fibonacciBigRec( n, mem );
    }

    private static BigInteger fibonacciBigRec( int n, BigInteger[] mem ) {

        if ( mem[n] == BIG_UNKNOWN ) {

            if ( n < 2 ) {
                mem[n] = new BigInteger( "1" );
            } else {
                mem[n] = fibonacciBigRec( n-2, mem ).add( fibonacciBigRec(n-1,mem) );
            }}
        return mem[n];
    }

    private static final BigInteger BIG_UNKNOWN = null;


// Diversa versione che applica una tecnica di programmazione dinamica:

    public static long fibonacciDp( int n ) {  // n >= 0

        long[] mem = new long[ n+1 ];

        mem[0] = 1;
        if ( n > 0 ) {
            mem[1] = 1;
        }
        for ( int k=2; k<=n; k=k+1 ) {

            mem[k] = mem[k-2] + mem[k-1];
        }
        return mem[n];
    }


/* Problema dei "Percorsi di Manhattan"

  (define manhattan
    (lambda (i j)    ; i, j naturali
      (if (or (= i 0) (= j 0))
          1
          (+ (manhattan (- i 1) j) (manhattan i (- j 1)))
          )
      ))
*/

// Traduzione in Java:

    public static long manhattan( int i, int j ) {  // i, j >= 0

        if ( (i == 0) || (j == 0) ) {
            return  1;
        } else {
            return  manhattan( i-1, j ) + manhattan( i, j-1 );
        }
    }


// Nuova versione che applica la tecnica di memoization:

    public static long manhattanMem( int i, int j ) {  // i, j >= 0

        long[][] mem = new long[ i+1 ][ j+1 ];

        for ( int u=0; u<=i; u=u+1 ) {
            for ( int v=0; v<=j; v=v+1 ) {
                mem[u][v] = UNKNOWN;
            }}
        return manhattanRec( i,j, mem );
    }

    private static long manhattanRec( int i, int j, long[][] mem ) {

        if ( mem[i][j] == UNKNOWN ) {

            if ( (i == 0) || (j == 0) ) {
                mem[i][j] = 1;
            } else {
                mem[i][j] = manhattanRec( i-1, j, mem ) + manhattanRec( i, j-1, mem );
            }}
        return mem[i][j];
    }


// Altra versione che applica una tecnica di programmazione dinamica:

    public static long manhattanDp( int i, int j ) {  // i, j >= 0

        long[] mem = new long[j+1];       // Economia di spazio di memoria

        for ( int v=0; v<=j; v=v+1 ) {    // manhattan(0,v), v in [0,j]
            mem[v] = 1;
        }
        for ( int u=1; u<=i; u=u+1 ) {
            for ( int v=1; v<=j; v=v+1 ) {  // manhattan(u,v), v in [0,j]
                mem[v] = mem[v-1] + mem[v];
            }
        }
        return mem[j];                    // manhattan(i,j)
    }


}  // class Memoization

