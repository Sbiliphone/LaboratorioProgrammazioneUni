package Problema11;

public class Laboratorio3 {
    public static void main(String[] args){

        //roundTable.serveNeighbour();
        //roundTable.passJug();
        //System.out.println(roundTable.knightWithJug());
        System.out.println(josephus(7));
    }

    private static IntSList josephus(int num){
        RoundTable roundTable = new RoundTable(num);
        while (roundTable.numberOfKnights()>2){
            roundTable = roundTable.passJug();
            roundTable = roundTable.serveNeighbour();
            System.out.println(roundTable.knightWithJug());
        }
        return roundTable.getHead();
    }
}
