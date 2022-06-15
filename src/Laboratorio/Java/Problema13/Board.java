package Laboratorio.Java.Problema13;

// Rompicapo delle "n regine" (20/04/12)


/*
 * Dato astratto "Scacchiera"
 *
 * Con riferimento al protocollo definito in Scheme...
 *
 * Protocollo:
 *
 *   b = new Board( n )           :  (empty-board n)
 *
 *   b.size()                     :  (size B)
 *
 *   b.queensOn()                 :  (queens-on B)
 *
 *   b.underAttack( i, j )        :  (under-attack? B i j)
 *
 *   b.addQueen( i, j )           :  (add-queen B i j)
 *
 *   b.removeQueen( i, j )        :  Rirpistino di uno stato precedente
 *
 *   b.arrangement()              :  (arrangement B)
 */


// Realizzazione del dato astratto "Scacchiera"


public class Board {
	 
	  private final int size;
	  private int queens;  
	  private SList<Integer> rowsUnderAttack;
	  private SList<Integer> colsUnderAttack;
	  private SList<Integer> dg1sUnderAttack; //Diagonale che scende da destra
	  private SList<Integer> dg2sUnderAttack; //Diagonale che sale da sinistra
	  private String config;
	  
	  private static final String ROWS= " 123456789ABCDEF";
	  private static final String COLS=" abcdefghijklmno";
	  
	  //costruttore
	  public Board(int n) {
		  size = n;
		  queens = 0;
		  rowsUnderAttack = new SList<Integer>();
		  colsUnderAttack=new SList<Integer>();
	      dg1sUnderAttack=new SList<Integer>(); 
	      dg2sUnderAttack=new SList<Integer>();	   
	      config = "";    
	  }	
	  
	  private Board (int s, int q, SList<Integer> rUA, SList<Integer> cUA, SList<Integer> dg1UA, SList<Integer> dg2UA, String c){
	       size = s;
	       queens = q;
	       rowsUnderAttack = rUA;
	       colsUnderAttack = cUA;
	       dg1sUnderAttack = dg1UA;
	       dg2sUnderAttack = dg2UA;
	       config = c;
	      }
	  
	  public int size(){
	    return size;
	  }
	  
	  public int queensOn(){
	   return queens;
	  }
	  
	  public boolean underAttack(int i, int j){
        return (
               (appartieneAllaLista(i, rowsUnderAttack)) ||
               (appartieneAllaLista(j, colsUnderAttack)) ||
               (appartieneAllaLista(i-j, dg1sUnderAttack)) ||
               (appartieneAllaLista(i+j, dg2sUnderAttack))
        );
      }
	  
	  private boolean appartieneAllaLista(int n, SList<Integer> l) {
		  for(int i = 0; i < l.length(); i++) {
			  if(n == l.listRef(i))
				  return true;
		  }
		  return false;
	  }
	  
	  public Board addQueen (int i, int j)
      {
          return new Board(
              this.size,
              queens + 1,
              rowsUnderAttack.cons(i),
              colsUnderAttack.cons(j),
              dg1sUnderAttack.cons(i-j),
              dg2sUnderAttack.cons(i+j),
              code(i,j)+ config
          );    
      }
	  
	  
	  private String code(int i, int j) {
	   return ( " " + COLS.charAt(j) + ROWS.charAt(i) + " ");  
	  }	  
	  
	  public String arrangement(){
	   return config;
	  }
	  
	  public String toString() {
		  return this.arrangement();
	  }
	    
}