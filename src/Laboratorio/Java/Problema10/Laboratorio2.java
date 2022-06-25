package Laboratorio.Java.Problema10;

public class Laboratorio2 {
    public static void main(String[] args){
        System.out.println(btrListSucc("+-", 5));
    }

    public static StringSList btrListSucc(String btr, int n){
        StringSList list= new StringSList(btr, new StringSList());
        for (int i=0; i<5-1; i++){
            list = new StringSList(Laboratorio1.btrSucc(list.car()), list);
        }
        return list.reverse();
    }
}
