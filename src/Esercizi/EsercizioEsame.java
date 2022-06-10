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

    public static String manhattanPath(int[][] manh){
        int m = manh.length -1;
        int n = manh[0].length -1;

        String path = "";

        int j = 0;
        int i = 0;
        while((i<m) && (j<n) && (manh[i][j] > 0)){
            if(reward(i,j,0)+manh[i+1][j] > reward(i,j,0)+manh[i][j+1]){
                i++;
                path += "0";
            } else {
                j++;
                path+="1";
            }
        }
        while (i<m){
            i++;
            path += "0";
        }
        while(j<n){
            j++;
            path +="1";
        }
        return path;
    }

    public static int reward(int i, int j, int step){
        return 5;
    }

    public static long rec(int x, int y, int z){
        if((x == 1) || (y == z) ){
            return 1;
        }else {
            return rec(x-1, y, z) + x * rec(x, y+1, z);
        }
    }

    public static long recDP(int x, int y, int z){
        long[][] mem = new long[x+1][z+1];

        for(int i=1; i<=x; i++){
            for (int j=z; j>=y; j++){
                if((i==1) || (j==z)){
                    mem[i][j] = 1;
                }else {
                    mem[i][j] = mem[i-1][j] + i * mem[i][j+1];
                }
            }
        }
        return mem[x][y];
    }
}
