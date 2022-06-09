package Laboratorio.Problema10;

public class StringSList {
    public static final StringSList NULL_SLIST = new StringSList();

    private final boolean empty;
    private final String first;
    private final StringSList rest;

    public StringSList() {
        empty = true;
        first = "";
        rest = null;
    }

    public StringSList(String e, StringSList il ) {
        empty = false;
        first = e;
        rest = il;
    }

    public boolean isNull() {
        return empty;
    }

    public String car() {
        return first;
    }

    public StringSList cdr() {
        return rest;
    }

    public StringSList cons(String e){
        return new StringSList(e, this);
    }

    public int length(){
        if ( isNull() ) {
            return 0;
        } else {
            return ( 1 + cdr().length() );
        }
    }

    public String listRef(int k){
        if ( k == 0 ) {
            return car();
        } else {
            return ( cdr().listRef(k-1) );
        }
    }

    public boolean equals(StringSList s1){
        if ( isNull() || s1.isNull() ) {
            return ( isNull() && s1.isNull() );
        } else if ( car() == s1.car() ) {
            return cdr().equals( s1.cdr() );
        } else {
            return false;
        }
    }

    public StringSList append(StringSList s1){
        if ( isNull() ) {
            return s1;
        } else {
            return ( cdr().append(s1) ).cons( car() );
        }
    }

    public StringSList reverse(){
        return reverseRec( new StringSList() );
    }

    public StringSList reverseRec(StringSList re){
        if ( isNull() ) {
            return re;
        } else {
            return cdr().reverseRec( re.cons(car()) );
        }
    }

    public String toString(){
        if ( isNull() ) {
            return "()";
        } else {
            String rep = "(" + car();
            StringSList r = cdr();
            while ( !r.isNull() ) {
                rep = rep + ", " + r.car();
                r = r.cdr();
            }
            return ( rep + ")" );
        }
    }
}
