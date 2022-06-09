package Laboratorio.Problema13;
/*
 * Classe SList<T>: Scheme-like Lists di elementi di tipo T
 *
 * Definizione di una classe in Java per realizzare oggetti
 * assimilabili alle liste in Scheme.
 *
 * Le liste create sono "immutabili".
 *
 *
 * ----- Protocollo -----
 *
 *   SList<T> s, t, u;                     // Tipi di riferimento
 *   int n;
 *
 * Costruttori:                            // Scheme:
 *
 *     s = new SList<T>();                 // null
 *     t = new SList<T>(n,s);              // cons
 *
 * Metodi:                                 // Scheme:
 *
 *     boolean b = t.isNull();             // null?
 *     n = t.car();                        // car
 *     u = t.cdr();                        // cdr
 *     u = t.cons(n);                      // cons (diversa versione)
 */


public class SList<T> {                    // Scheme-Like Lists of generic element


  // ----- Costante lista vuota (condivisa)
  
  //public static final IntSList NULL_INTLIST = new IntSList();
  
  
  // ----- Rappresentazione interna di una lista: private!
  
  private final boolean empty;             // oggetti immutabili:
  private final T first;                   // variabili di istanza "final"
  private final SList<T> rest;
  
  
  // ----- Operazioni di base sulle liste, mutuate da Scheme
  
  public SList() {                      // creazione di una lista vuota
	  empty = true;
	  first = null;                             // valore irrilevante in questo caso
	  rest = null;
  }
  
  public SList(T x, SList<T> sl) {  // creazione di una lista non vuota:
	  empty = false;
	  first = x;
	  rest = sl;
  }
  
  
  public boolean isNull() {                // verifica se una lista e' vuota
	  return empty;
  }
  

  public T car() {                       // primo elemento di una lista
	  return first;                          // si assume: lista non vuota
  }
  
  
  public SList<T> cdr() {                  // resto di una lista
	  return rest;                           // si assume: lista non vuota
  }
  
  
  // ----- Realizzazione alternativa (sostanzialmente equivalente) del "cons"
  
  public SList<T> cons(T x){          // costruzione di nuove liste
	  return new SList<T>(x, this);
  }
  
  
  // ----- Operazioni aggiuntive, definite in termini dei precedenti metodi
  
  public int length(){                    // lunghezza di una lista
	  if (isNull()){                      // oppure: this.isNull()
	    return 0;
	  }else{
	    return (1 + cdr().length());       // oppure:
	  }                                      //   ( this.cdr() ).isNull()
  }
  
  
  public T listRef(int k){            // elemento in posizione k
	  if (k == 0) {
	    return car();                        // oppure: this.car()
	  } else {
	    return (cdr().listRef(k-1));       // oppure:
	                                         //   ( this.cdr() ).listRef(k-1)
	  }
  }                                        // etc.: this. implicito
  
  
  public boolean equals(SList<T> sl){   // contronto di liste
	  if (isNull() || sl.isNull()) {
	    return (isNull() && sl.isNull());
	  } else if (car() == sl.car()) {
	    return cdr().equals(sl.cdr());
	  } else {
	    return false;
	  }
  }
  
  
  public SList<T> append(SList<T> sl){  // fusione di liste
	  if (isNull()){
	    return sl;
	  } else{
	    // return new IntSList( car(), cdr().append(il) );
	    return (cdr().append(sl)).cons(car());
	  }
  }
  
  
  public SList<T> reverse(){              // rovesciamento di una lista
	  return reverseRec(new SList<T>());
  }
  
  private SList<T> reverseRec(SList<T> re){
	  if (isNull()) {                      // metodo di supporto: private!
	    return re;
	  } else{
	    // return cdr().reverseRec( new IntSList(car(),re) );
	    return cdr().reverseRec(re.cons(car()));
	  }
  }
  
  
  // ----- Rappresentazione testuale (String) di una lista
  
  public String toString(){               // ridefinizione del metodo generale per la visualizzazione testuale
	  if (isNull()) {
	    return "()";
	  } else {
	    String rep = "(" + car();
	    SList<T> r = cdr();
	    while (!r.isNull()) {
	      rep = rep + ", " + r.car();
	      r = r.cdr();
	    }
	    return (rep + ")");
	  }
  }


}  // class SList<T>

