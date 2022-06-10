package Esercizi;

import java.util.Stack;

public class Hanoi {
    public String hanoi(Towers hts, int d){
        hanoiRec(hts.height(), d, hts);
        return hts.moves();
    }

    public static void hanoiRec(int n, int d, Towers hts){
        if(n>0){
            if(hts.site(n) == d){
                hanoiRec(n-1, d, hts);
            }else {
                int t = hts.transit(n, d);
                hanoiRec(n-1, t, hts);
                hanoiRec(-n, d, hts);
                hanoiRec(n-1, d, hts);
            }
        }else if(n<0){
            hts.move(-n, d);
        }
    }

    public static String hanoiIter(Towers hts, int d){
        int n = hts.height();
        Stack<int[]> stk = new Stack<int[]>();
        stk.push(new int[]{n, d});

        while(!stk.empty()){
            int[] f = stk.pop();
            n = f[0];
            d = f[1];

            if(n>0){
                if(hts.site(n) == d){
                    stk.push(new int[]{n-1, d});
                }else {
                    int t = hts.transit(n, d);
                    stk.push(new int[]{n-1, d});
                    stk.push(new int[]{-n, d});
                    stk.push(new int[]{n-1, t});
                }
            }else if(n<0){
                hts.move(-n, d);
            }
        }
        return hts.moves();
    }

}
