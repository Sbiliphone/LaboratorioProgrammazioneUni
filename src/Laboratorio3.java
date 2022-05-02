public class Laboratorio3 {
    public static void main(String[] args){
        josephus(7);
    }

    private static IntSList josephus(int num){
        RoundTable roundTable = new RoundTable(num);
        while (roundTable.numberOfKnights()>2){
            System.out.println("inizio: " + roundTable.knightWithJug());
            roundTable.passJug();
            roundTable.serveNeighbour();
            roundTable.passJug();
            System.out.println("fine: " + roundTable.knightWithJug());
        }
        return roundTable.getTail();
    }
}