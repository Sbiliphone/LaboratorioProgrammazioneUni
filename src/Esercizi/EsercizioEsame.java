package Esercizi;

public class EsercizioEsame {
    public static int llis(double[] s){
        return llisRec(s, 0, 0);
    }

    private static int llisRec(double[] s, int i, double t){
        if(i==s.length){
            return 0;
        } else if((t==0) || ((0.5*t <= s[i]) && (s[i] < t))){
            return Math.max(1+llisRec(s, i+1, s[i]), llisRec(s, i+1, t));
        } else {
            return llisRec(s, i+1, t);
        }
    }

    public static int llisMem(double[] s){
        int n = s.length;

        int[][] mem = new int[n+1][n+1];
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                mem[i][j] = -1;
            }
        }
        return llisMRec(s, 0, n, mem);
    }

    private static int llisMRec(double[] s, int i, int j, int[][] mem){
        double t = (j==s.length)?0:s[j];
        if(mem[i][j] == -1) {
            if (i == s.length) {
                mem[i][j] = 0;
            } else if ((t == 0) || ((0.5 * t <= s[i]) && (s[i] < t))) {
                mem[i][j] = Math.max(1 + llisMRec(s, i + 1, j, mem), llisMRec(s, i + 1, j, mem));
            } else {
                mem[i][j] = llisMRec(s, i + 1, j, mem);
            }
        }
        return mem[i][j];
    }
}
