package Laboratorio.Java.Problema11;

public class Laboratorio3 {
    public static void main(String[] args){
        //RoundTable roundTable = new RoundTable(2);
        //System.out.println(new IntSList(roundTable.knightWithJug(), roundTable.getHead()));
        //System.out.println(roundTable.knightWithJug());
        System.out.println(josephus(8));
    }

    private static IntSList josephus(int num){
        RoundTable roundTable = new RoundTable(num);
        while (roundTable.numberOfKnights()>2){
            roundTable = roundTable.serveNeighbour();
            roundTable = roundTable.passJug();
            System.out.println(roundTable.knightWithJug());
        }
        return new IntSList(roundTable.knightWithJug(), roundTable.getHead());
    }
}
