package Problema12;
/*
 *
 * Protocollo:
 *
 *   b = new Problema12.Board( n )           :  creazione di una scacchiera nxn vuota
 *
 *   b.size()                     :  dimensione della scacchiera
 *
 *   b.queensOn()                 :  numero di regine collocate sulla scacchiera
 *
 *   b.underAttack( i, j )        :  la posizione di coordinare <i,j> � minacciata?
 *
 *   b.addQueen( i, j )           :  nuova scacchiera con una regina in posizione <i,j>
 *   								 che si aggiunge alla configurazione di b
 *
 *   b.removeQueen( i, j )        :  ripristino di uno stato precedente
 *
 *   b.arrangement()              :  codifica testuale della configurazione
 */



public class Board {

    private final int size; // dimensione scacchiera
    private int queens; // n regine collocate nella scacchiera
    private IntSList rowsUnderAttack; // righe
    private IntSList colsUnderAttack; // colonne
    private IntSList dg1sUnderAttack; // diagonale che scende da destra
    private IntSList dg2sUnderAttack; // diagonale che sale da sinistra
    private String config; // codifica testuale della configurazione

    private static final String ROWS= " 123456789ABCDEF";
    private static final String COLS=" abcdefghijklmno";

    //costruttore
    public Board(int n) {
        size = n;
        queens = 0;
        rowsUnderAttack = new IntSList();
        colsUnderAttack = new IntSList();
        dg1sUnderAttack = new IntSList();
        dg2sUnderAttack = new IntSList();
        config = "";
    }

    private Board (int s, int q, IntSList rUA, IntSList cUA, IntSList dg1UA, IntSList dg2UA, String c){
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

    // cerco in ogni riga, colonna e diagonali se la posizione passata per argomento � sotto attacco
    // cio� se � presente una regina
    public boolean underAttack(int i, int j){
        return (
                (appartieneAllaListaRec(i, rowsUnderAttack)) ||
                        (appartieneAllaListaRec(j, colsUnderAttack)) ||
                        (appartieneAllaListaRec(i-j, dg1sUnderAttack)) ||
                        (appartieneAllaListaRec(i+j, dg2sUnderAttack))
        );
    }

    // ricerca iterativa
    private boolean appartieneAllaLista(int n, IntSList l) {
        for(int i = 0; i < l.length(); i++) {
            if(n == l.listRef(i))
                return true;
        }
        return false;
    }

    // ricerca ricorsiva, sfruttando la natura di IntSList
    private boolean appartieneAllaListaRec(int n, IntSList l) {
        int i = 0;
        while(i < l.length()) {
            if(n == l.listRef(i)) {
                return true;
            }
            else {
                i++;
                l.cdr();
            }
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