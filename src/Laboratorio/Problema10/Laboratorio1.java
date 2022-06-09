package Laboratorio.Problema10;

public class Laboratorio1 {
    public static void main(String[] args){
        System.out.println(btrSucc("--++."));
        System.out.println(onesComplement("0011"));
    }
    /*
    (define btr-succ
            (lambda (btr)
    (let ((n (string-length btr)))
            (let ((lsb (string-ref btr (- n 1))))
            (if (= n 1)
            (if (char=? lsb #\+)
            "+-"
            "+")
            (let ((pre (substring btr 0 (- n 1))))
            (if (char=? lsb #\+)
            (string-append (btr-succ pre) "-")
            (string-append pre (if (char=? lsb #\-) "." "+"))
            ))
            ))) ))
     */
    public static String btrSucc(String btr){
        int n = btr.length();
        int lsb = btr.charAt(n-1);
        if(n == 1){
            if(lsb == '+'){
                return "+-";
            }else {
                return "+";
            }

        }else {
            String pre = btr.substring(0, n-1);
            if(lsb == '+'){
                return btrSucc(pre) + "-";
            }else{
                if(lsb == '-'){
                    return pre + ".";
                }else {
                    return pre + "+";
                }
            }
        }
    }
/*
    (define ones-complement  ; val: stringa di 0/1
            (lambda (bin)          ; bin: stringa di 0/1
            (if (string=? bin "")
            ""
            (string-append
            (ones-complement (substring bin 0 (- (string-length bin) 1)))
            (bit-complement (substring bin (- (string-length bin) 1)))
            ))
            ))
 */
    public static String onesComplement(String bin){
        if(bin.equals("")){
            return "";
        }else{
            char [] binC = bin.toCharArray();
            for(int i=0; i<bin.length(); i++){
                if(binC[i] == '0'){
                     binC[i] = '1';
                }else {
                    binC[i] = '0';
                }
            }
            bin = new String(binC);
            return bin;
        }
    }
}


