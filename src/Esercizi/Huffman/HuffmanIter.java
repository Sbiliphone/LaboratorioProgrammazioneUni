package Esercizi.Huffman;
import java.util.*;

import huffman_toolkit.*;

public class HuffmanIter {

    /*
     * Codifica di Huffman:
     * Rivisitazione di alcune procedure di supporto
     *
     * Da ricorsione a iterazione con il supporto di uno stack
     *
     * Ultimo aggiornamento: 21/05/2021
     */




// Un file di codice sorgente Java (.java) puo' contenere la definizione
// di una sola classe pubblica, ma anche di eventuali ulteriori classi
// soggette a restrizioni di accessibilita' (non "public")


// Strutture dati di supporto per i frame dello stack:
// per semplificare il codice, le istanze delle classi Frame1 e Frame2
// sono trattate come meri aggregati di dati eterogenei (record/struct)
// e non come oggetti in senso proprio; quindi e' consentito l'accesso
// pubblico alle variabili di istanza = campi dei record/struct

    class Frame1 {

        public final Node node;
        public final String path;

        public Frame1( Node n, String p ) {

            node = n;
            path = p;
        }

    }  // class Frame1

    class Frame2 {

        public int state;
        public Node left, right;

        public Frame2() {

            state = 0;     // avvio della ricorsione
            left  = null;  // per registrare i risultati
            right = null;  // parziali da utilizzare
        }

    }  // class Frame2


// Versioni iterative dei metodi ricorsivi della classe Huffman
// che si avvalgono di uno stack


    public class Stacks {


        private static final int CHARS = InputTextFile.CHARS;


        // 3.
        // Tabella di codifica dei caratteri:
        // root: nodo radice dell'albero di Huffman

        public  String[] huffmanCodesTable( Node root ) {

            String[] codes = new String[ CHARS ];                   // tabella dei codici di Huffman

            Stack<Frame1> stack = new Stack<Frame1>();              // stack dei sottoalberi da visitare

            stack.push( new Frame1(root,"") );                      // visita dell'albero di Huffman

            do {

                Frame1 current = stack.pop();                         // prossimo sottoalbero da visitare
                Node n = current.node;
                String code = current.path;

                if ( n.isLeaf() ) {

                    codes[ n.character() ] = code;                      // nodo foglia: code = codice del carattere

                } else {

                    stack.push( new Frame1(n.right(),code+"1") );       // nodo intermedio: visita dei sottoalberi
                    stack.push( new Frame1(n.left(), code+"0") );       // prima (in cima) il sottoalbero sinistro
                }
            } while ( !stack.empty() );                             // visita da completare

            return codes;
        }

  /* Versione ricorsiva per confronto:

  public static String[] huffmanCodesTable( Node root ) {

    String[] codes = new String[ CHARS ];

    fillTable( root, "", codes );

    return codes;
  }

  private static void fillTable( Node n, String code, String[] codes ) {

    if ( n.isLeaf() ) {
      codes[ n.character() ] = code;
    } else {
      fillTable( n.left(),  code+"0", codes );
      fillTable( n.right(), code+"1", codes );
    }
  }

  */

  /* Oppure, versione iterativa senza il ricorso a frame strutturati:

  public static String[] huffmanCodesTable( Node root ) {

    String[] codes = new String[ CHARS ];                   // tabella dei codici di Huffman

    Stack<Node> stack = new Stack<Node>();                  // stack dei sottoalberi da visitare

    stack.push( root );                                     // visita dell'albero di Huffman
    String code = "";                                       // codice associato al nodo in cima allo stack

    do {

      Node n = stack.pop();                                 // prossimo sottoalbero da visitare

      if ( n.isLeaf() ) {

        codes[ n.character() ] = code;                      // nodo foglia: code = codice del carattere

        int k = code.lastIndexOf( '0' );
        if ( k >= 0 ) {
          code = code.substring( 0, k ) + "1";              // codice associato al nodo in cima allo stack
        }
      } else {

        stack.push( n.right() );                            // nodo intermedio: visita dei sottoalberi
        stack.push( n.left()  );                            // prima (in cima) il sottoalbero sinistro

        code = code + "0";                                  // codice associato al nodo in cima allo stack
      }
    } while ( !stack.empty() );                             // visita da completare

    return codes;
  }

  */


        // 4.
        // Codifica lineare dell'albero di Huffman tramite visita ricorsiva
        // n: nodo visitato
        // Struttura:
        // - una foglia e' codificata dal carattere che rappresenta
        //   (i caratteri speciali '@' e '\' sono preceduti da '\')
        // - un albero con piu' di un nodo e' codificato
        //   linearizzando i sottoalberi sinistro e destro,
        //   quindi giustapponendo il simbolo '@' e le stringhe ottenute

        private  String flattenTree( Node root ) {

            Stack<Node> stack = new Stack<Node>();
            String flat = "";

            stack.push( root );                                     // radice dell'albero di Huffman

            do {

                Node n = stack.pop();

                if ( n.isLeaf() ) {                                   // foglia: codifica del carattere

                    char c = n.character();

                    if ( (c == '\\') || (c == '@') ) {
                        flat = flat + "\\" + c;                           // caratteri speciali: \, @
                    } else {
                        flat = flat + c;                                  // altri caratteri
                    }
                } else {

                    flat = flat + "@";                                  // @

                    stack.push( n.right() );                            // codifica del sottoalbero destro
                    stack.push( n.left() );                             // dopo la codifica del sottoalbero sinistro
                }
            } while ( !stack.empty() );

            return flat;
        }

  /* Versione ricorsiva per confronto:

  public static String flattenTree( Node n ) {

    if ( n.isLeaf() ) {
      char c = n.character();
      if ( (c == '\\') || (c == '@') ) {
        return ( "\\" + c );
      } else {
        return ( "" + c );
      }
    } else {
      return ( "@"
             + flattenTree( n.left() )
             + flattenTree( n.right() )
               );
    }
  }

  */


        // 6.
        // Ricostruzione dell'albero di Huffman dalla sua codifica lineare
        // in: documento compresso
        // Struttura:
        // - una foglia e' rappresentata dal carattere corrispondente
        // - un albero con piu' di un nodo e' rappresentato
        //   dalla linearizzazione dei sottoalberi sinistro e destro,
        //   precedute dal simbolo '@'
        //
        // Interpretazione degli elementi inseriti nello stack:
        // - null: nodo in via di definizione = ricorsione non ancora completata
        // - nodo: figlio sinistro = dato di una ricorsione non completata (null immediatamente sotto nello stack)
        //         registrato temporaneamente in attesa che il corrispondente figlio destro sia disponibile

        public  Node restoreTree( InputTextFile in ) {

            Stack<Frame2> stack = new Stack<Frame2>();
            stack.push( new Frame2() );                             // frame corrispondente all'invocazione
            // della versione ricorsiva di "restoreTree"
            Node n = null;                                          // n destinato a registrare il risultato
            // dell'ultima ricorsione competata
            do {

                Frame2 current = stack.peek();                        // invocazione in corso di elaborazione:
                // il frame verra' rimosso solo alla fine
                switch ( current.state ) {

                    case 0 : {                                          // stato 0: avvio di un'invocazione:
                        char c = (char) in.readChar();                    // simbolo successivo della codifica dell'albero
                        if ( c == '@' ) {                                 // '@' ?
                            stack.push( new Frame2() );                     // prima invocazione ricorsiva
                            current.state = 1;                              // cambia lo stato dell'invocazione corrente
                        } else {                                          // carattere: caso base
                            if ( c == '\\' ) {
                                c = (char) in.readChar();                     // simbolo speciale (skip)
                            }
                            n = new Node( c, 0 );                           // n = risultato che conclude un'invocazione
                            stack.pop();                                    // frame rimosso dallo stack
                        }
                        break;
                    }
                    case 1 : {                                          // stato 1: prima ricorsione completata:
                        current.left = n;                                 // il risultato n viene associato al frame
                        stack.push( new Frame2() );                       // seconda invocazione ricorsiva
                        current.state = 2;                                // cambia lo stato dell'invocazione corrente
                        break;
                    }
                    case 2 : {                                          // stato 2: seconda ricorsione completata:
                        current.right = n;                                // il risultato n viene associato al frame
                        n = new Node( current.left, current.right );      // n = risultato che conclude un'invocazione
                        stack.pop();                                      // frame rimosso dallo stack
                    }
                }                                                     // fine costrutto switch
            } while ( !stack.empty() );

            return n;                                               // radice dell'albero di Huffman
        }

  /* Versione ricorsiva per confronto:

  public static Node restoreTree( InputTextFile in ) {

    char c = (char) in.readChar();

    if ( c == '@' ) {

      Node left  = restoreTree( in );

      Node right = restoreTree( in );

      return new Node( left, right );

    } else {
      if ( c == '\\' ) {
        c = (char) in.readChar();
      }
      return new Node( c, 0 );
    }
  }

  */

  /* Oppure, versione iterativa senza il ricorso a frame strutturati:

  public static Node restoreTree( InputTextFile in ) {

    Stack<Node> stack = new Stack<Node>();
    Node n = null;

    do {

      char c = (char) in.readChar();                        // simbolo successivo della codifica dell'albero

      if ( c == '@' ) {                                     // '@' ?

        stack.push( null );                                 // segnaposto per la creazione di un nuovo nodo
        // di cui al momento non si conoscono i figli
      } else {
        // carattere
        if ( c == '\\' ) {
          c = (char) in.readChar();                         // simbolo speciale (skip)
        }
        n = new Node( c, 0 );                               // il nodo puo' essere creato immediatamente

        while ( !stack.empty() && (stack.peek() != null) ) {

          n = new Node( stack.pop(), n );                   // costruzione di un nodo per cui sono disponibili
        }                                                   // i figli
        if ( !stack.empty() )  {

          stack.pop();                                      // rimozione del segnaposto (null)
          stack.push( n );                                  // n nello stack in attesa del nodo fratello
        }
      }
    } while ( !stack.empty() );

    return n;                                               // radice dell'albero di Huffman
  }

  */


    }  // class Stacks


}
