package Problema11;

public class Laboratorio3 {
    public static void main(String[] args){
        RoundTable roundTable = new RoundTable(24);
        //roundTable.serveNeighbour();
        //roundTable.passJug();
        //System.out.println(roundTable.knightWithJug());
        System.out.println(josephus(roundTable));
    }

    private static IntSList josephus(RoundTable roundTable){
        while (roundTable.numberOfKnights()>2){
            System.out.println("inizio: " + roundTable.knightWithJug());
            roundTable = roundTable.passJug();
            roundTable = roundTable.serveNeighbour();
            roundTable.dopoUscitaCavaliere();
            System.out.println("fine: " + roundTable.knightWithJug());
        }
        return roundTable.getTail();
    }
}
