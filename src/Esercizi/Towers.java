package Esercizi;

public class Towers {
    private int[] site;
    String moves;


    public Towers(int n){
        site= new int[n+1];
        moves = "";
    }

    public void put(int disk, int rod){
        site[disk] = rod;
    }

    public void move(int disk, int dst){
        moves = moves + " " + site[disk] + "->" + dst;
        site[disk] = dst;
    }

    public int height(){
        return site.length -1;
    }

    public int site(int disk){
        return site[disk];
    }

    public int transit(int disk, int dst){
        return (6-site(disk) -dst);
    }

    public String moves(){
        return moves;
    }
}
