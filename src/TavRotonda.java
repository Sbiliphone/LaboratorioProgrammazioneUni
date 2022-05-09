
/**
 * realizzazione di un gioco simil tavola rotonda dove ogni giocatore deve versare sulla borcca di quello alla sua sx e chi riceve esce dal gioco (chi versa rimane dentro)
 * 
 * costruttore:
 * new TavRotonda(n)         : [TavRotonda]
 * 
 * domande da fare al programma:
   tav.quantiCavalieri()     : [int]         == quanti cavalieri ci sono?
   tav.chiHaLaBrocca()       : [int]         == in che posizione si trovava il cavaliere all'inizio quando tutti erano disposti (tra 1 e n)?
   tav.ritualeCompletato()   : [boolean]     == true se è rimasto un solo cavaliere; false se ci sono più di un cavaliere
   tev.serviSidro()          : [TavRotonda]  == serve il sidro?
   tav.passaBrocca()         : [TavRotonda]  == gli viene servito il sidro?
 */
public class TavRotonda {
    
    private final int commensali;                             // numero commensali nella tavola da 1 a n
    private final int cavConBrocca;                           // chi ha la brocca?
    private final IntSList altriCavv;                         // lista dei cavlieri senza quello con la brocca 
    private final IntSList altriRovesc;                       // (lista dei cavlieri serviti)??

    // final ne esplicita il fatto che questi oggetti sono immutabili dal menpomento che vengono creati 
    
    public TavRotonda( int n ) {                              // creare un intervallo da 1 a n
        
        commensali = n;
        cavConBrocca = 1;
        altriCavv = range( 2, n );                            // parte dal secondo cavalieri perchè il primo deve essere quello con la brocca e non rientrare in questa lista
        altriRovesc = IntSList.NULL_INTLIST;                 // lista vuota perchè non ci sono ancora 
    }
    
    private TavRotonda( int n, int brocca, IntSList  lista1, IntSList  lista2 ){  //com'è la rappresentazione interna
        
        commensali = n;
        cavConBrocca = brocca;
        altriCavv = lista1;
        altriRovesc = lista2;
    }
    
    public int quantiCavalieri() {
        
        return commensali;  
    }
    
    public int chiHaLaBrocca() {
        
        return cavConBrocca;
    }
    
    public boolean ritualeCompletato() {
        
        return ( commensali == 1 );                         // ci sono altri cavalieri? se ne è rimasto uno è comne se fosse finito
    }
    
    public TavRotonda serviSidro() {
        
        if( commensali == 1 ){                               //se c'è solo il commensale con la brocca, non si può accettare la tavola con solo 1 ma soprattutto con solo quello con la brocca
            
            return this;                                     // non cambia nulla
            
        } else if (altriCavv.isNull() ) {                    // se è vuota si deve rovesciare altrimenti è più semplice
        // se la prima lista non è vuota, il suo primo elmento viene servito e deve uscire
             
            IntSList listaRovesc = altriRovesc.reverse();    // altriRovesc è la lista dei roverciati che con reverse torna dritta
                    
            return new TavRotonda( commensali - 1, cavConBrocca, listaRovesc.cdr(), IntSList.NULL_INTLIST ); 
            
        } else {                                             // altrimenti vuol dire che  ce n'è più di uno, quindi, il commensale serve un altro
        //si costruisce una nuova tavola rotonda con un commensale in meno, mantiene la brocca allo stesso cavaliere e fa uscire il cavaliere servito
        
            return new TavRotonda( commensali - 1, cavConBrocca, altriCavv.cdr(), altriRovesc );     
            
        }
    
    }
    
    public TavRotonda passaBrocca() {
        
        if( commensali == 1 ){                               // può esserci solo un commensale, ma la brocca non può essere passata a se stesso
            
            return this;                                     // non cambia nulla, restituisce se stesso
        
        } else if (altriCavv.isNull() ) {                    
            
            IntSList listaRovesc = altriRovesc.reverse();   
            IntSList lista2 = IntSList.NULL_INTLIST.cons( cavConBrocca );       
            
            return new TavRotonda( commensali, listaRovesc.car(), listaRovesc.cdr(), lista2 ); 
            
        } else {                                             // più di un commensale 
        // in passaBrocca non cambia il numero di commensali ma solo chi ha la brocca:

            return new TavRotonda( commensali, altriCavv.car(), altriCavv.cdr(), altriRovesc.cons(cavConBrocca) ); // quello con la brocca finisce in fondo (davanti alla lista dei rovesciati)
       
        }
    }
    
    public TavRotonda dopoUscitaCavaliere() {                // tutti i passi da una situazione a quella che segue immediatamente l'usicta
        
        TavRotonda nuovaTav = serviSidro();        // creo una prima tavola rotonda ottenuta facendo uscire un cavaliere basata sulla situazione attuale
        
        return nuovaTav.passaBrocca();             // creo una seconda tavola rotonda ottenuta passando la brocca basata sulla situazione intermedia data dalla tavoila creata qui sopra  
    }
    
    private static IntSList range ( int inf, int sup ){  //range di un intervallo di interi da un estremo inferiore ad uno superiore
        
        if ( inf > sup ){                                // se l'estremo sup è maggiore dell'inf la considero come na lista vuota di interi 
            
            return ( new IntSList() );
            
        } else {                                         //altrimenti metto il primo elemento davanti alla lista che ha tutti gli interi da inf+1 a sup
            
            return range(inf+1, sup).cons(inf) ; 
            
        }
    }
    
    
    
    
    
    
    
}
