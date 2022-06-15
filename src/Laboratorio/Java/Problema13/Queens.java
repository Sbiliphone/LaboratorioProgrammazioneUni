package Laboratorio.Java.Problema13;
import java.util.Scanner;


/*
 * Rompicapo delle "n regine"
 *
 * Ultimo aggiornamento: 12/04/2018
 *
 *
 * Dato astratto "configurazione della scacchiera":  Board
 *
 * Operazioni:
 *
 *   new Board( int n )           :  costruttore (scacchiera vuota)
 *
 *   size()                       :  int
 *
 *   queensOn()                   :  int
 *
 *   underAttack( int i, int j )  :  boolean
 *
 *   addQueen( int i, int j )     :  Board
 *
 *   arrangement()                :  String
 *
 *
 * Board b;
 *
 *   new Board(n)           costruttore della scacchiera n x n vuota;
 *   b.size()               dimensione n della scacchiera b;
 *   b.queensOn()           numero di regine collocate nella scacchiera b;
 *   b.underAttack(i,j)     la posizione <i,j> e' minacciata?
 *   b.addQueen(i,j)        scacchiera che si ottiene dalla configurazione b
 *                          aggiungendo una nuova regina in posizione <i,j>
 *                          (si assume che la posizione non sia minacciata);
 *   b.arrangement() :      descrizione "esterna" della configurazione
 *                          (convenzioni scacchistiche).
 */


public class Queens {


  public static final SList<Board> NULL_BOARDLIST = new SList<Board>();
  static Scanner in = new Scanner(System.in);
  
  public static void main( String args[] ) {
	  /*System.out.println("Inserisci la dimensione della scacchiera:");
	  int n = in.nextInt();
	    
	  System.out.println( numberOfSolutions(n) );
	  System.out.println( listOfAllSolutions(n) );*/
	  //esempi
	  System.out.println(numberOfSolutions(1)); // 1
	  System.out.println(numberOfSolutions(2)); // 0
	  System.out.println(numberOfSolutions(3)); // 0
	  System.out.println(numberOfSolutions(4)); // 2
	  System.out.println(numberOfSolutions(5)); // 10
	  System.out.println(numberOfSolutions(6)); // 4
	  System.out.println(numberOfSolutions(7)); // 40
	  System.out.println(numberOfSolutions(8)); // 92
	  System.out.println(numberOfSolutions(9)); // 352
	  System.out.println(numberOfSolutions(10)); // 724
	  }

  
  public static int numberOfSolutions( int n ) {
    
    return numberOfCompletions( new Board(n) );
  }
  
  

  private static int numberOfCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return 1;
    
    } else {
    
      int i = q + 1;
      int count = 0;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          count = count + numberOfCompletions( b.addQueen(i,j) );
      }}
      return count;
    }
  }
  
  
  /*
   * II. Lista delle soluzioni:
   *
   * Confronta il programma precedente!
   */
  
  public static SList<Board> listOfAllSolutions( int n ) {
  
    return listOfAllCompletions( new Board(n) );
  }
  
  
  private static SList<Board> listOfAllCompletions( Board b ) {
  
    int n = b.size();
    int q = b.queensOn();
    
    if ( q == n ) {
    
      return ( NULL_BOARDLIST.cons(b) );
    
    } else {
    
      int i = q + 1;
      SList<Board> solutions = NULL_BOARDLIST;
      
      for ( int j=1; j<=n; j=j+1 ) {
        if ( !b.underAttack(i,j) ) {
        
          solutions = solutions.append( listOfAllCompletions(b.addQueen(i,j)) );
      }}
      return solutions;
    }
  }
  
}  // class Queens
