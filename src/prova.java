public class prova {
    public static void main(String[] args){
        TavRotonda roundTable = new TavRotonda(4);
        roundTable.passaBrocca();
        roundTable.serviSidro();
        roundTable.dopoUscitaCavaliere();
        System.out.println(roundTable.quantiCavalieri());
        roundTable.serviSidro();
        roundTable.passaBrocca();
        System.out.println(roundTable.quantiCavalieri());
    }
}
