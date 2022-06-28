package Laboratorio.Java.Problema12;

public class Queens {
    //static Scanner in = new Scanner(System.in);

    public static void main( String args[] ) {

        //int n = in.nextInt();
        //System.out.println(numberOfSolutions(n));
        // esempi
        System.out.println(numberOfSolutions(1)); // 1
        System.out.println(numberOfSolutions(2)); // 0
        System.out.println(numberOfSolutions(3)); // 0
        System.out.println(numberOfSolutions(4)); // 2
        System.out.println(numberOfSolutions(5)); // 10
        System.out.println(numberOfSolutions(6)); // 4
        System.out.println(numberOfSolutions(7)); // 40
        System.out.println(numberOfSolutions(8)); // 92
        System.out.println(numberOfSolutions(9)); // 352
        System.out.println(numberOfSolutions(10)); // 724
    }



    public static int numberOfSolutions( int n ) {

        return numberOfCompletions( new Board(n) );
    }




    private static int numberOfCompletions( Board b ) {

        int n = b.size();
        int q = b.queensOn();

        if ( q == n ) {
            return 1;
        } else {
            int i = q + 1;
            int count = 0;

            for ( int j=1; j<=n; j=j+1 ) {
                if ( !b.underAttack(i,j) ) {

                    count = count + numberOfCompletions( b.addQueen(i,j) );
                }}
            return count;
        }
    }


}  // class Laboratorio.Java.Problema12.Queens
