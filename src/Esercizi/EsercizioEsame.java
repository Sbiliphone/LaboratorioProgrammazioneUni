package Esercizi;

import java.util.Stack;

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

    public static long st(int n, int k){
        long[] ct = new long[]{0};
        sRec(n, k, 1, ct);
        return ct[0];
    }

    public static void sRec(int n, int k, int q, long[] ct){
        if((k==1) || (k==n)){
            ct[0] = ct[0]+q;
        }else {
            sRec(n-1, k-1, q, ct);
            sRec(n-1, k, k*q, ct);
        }
    }

    public static long stIter(int n, int k){
        long[] ct = new long[]{0};
        Stack<int[]> stack= new Stack<int[]>();
        int[] f= new int[]{n, k, 1};
        stack.push(f);
        while(!stack.empty()){
            f= stack.pop();

            if((f[1]==1) || (f[1]==f[0])){
                ct[0] =ct[0] + f[2];
            }else {
                stack.push(new int[]{f[0]-1, f[1], f[1]*f[2]});
                stack.push(new int[]{f[0]-1, f[1]-1, f[2]});
            }
        }
        return ct[0];
    }

    public static int commonStretches(String u, String v){
        int count = 0;
        int n = u.length();
        int k = 0; // tratti complessivi percorsi
        int i = 0; // tratti verticali di u
        int j = 0; // tratti verticali di v

        while(k<n){
            if((i==j) && (u.charAt(k) == v.charAt(k))){
                count++;
            }
            if(u.charAt(k) == '0'){
                i++;
            }
            if(v.charAt(k) == '0'){
                j++;
            }
            k++;
        }
        return count;
    }

    public static int[] ldiff(String u, String v){
        int m = u.length();
        int n = v.length();

        int[][][] mem = new int[m+1][n+1][2];
        for(int i = 0; i<=m; i++){
            for(int j = 0; j<=n; j++){
                mem[i][j] = null;
            }
        }
        return ldiffRec(u, v, mem);
    }

    private static int[] ldiffRec(String u, String v, int[][][] mem){
        int i = u.length();
        int j = v.length();

        if(mem[i][j] == null){
            if(u.equals("") || v.equals("")){
                mem[i][j] = new int[]{u.length(), v.length()};
            }else if(u.charAt(0) == v.charAt(0)){
                mem[i][j] = ldiffRec(u.substring(1), v.substring(1), mem);
            }else{
                int[] x = ldiffRec(u.substring(1), v, mem);
                int[] y = ldiffRec(u, v.substring(1), mem);
                if(x[0] < y[0]){
                    mem[i][j] = new int[] {x[0]+1, x[1]};
                }else {
                    mem[i][j] = new int[] {y[0]+1, y[1]};
                }
            }
        }
        return mem[i][j];
    }

}
