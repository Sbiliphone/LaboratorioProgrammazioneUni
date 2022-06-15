;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema3) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define rep-sign
  (lambda (s)
    (if (char=? (string-ref s 0) #\-) ; se il primo carattere è uguale a - ...
        -1
        1
        )))
(define rep-int
  (lambda (s)
    (rep-int-rec s s)
    ))

(define rep-int-rec
  (lambda (s int)                                                        ; s: stringa originale, int: stringa con solo parte intera 
    (if (string=? int "")                                                ; se la stringa con la parte intera è vuota
        s                                                                ; restituisce la stringa originale
        (if (char=? (string-ref int (- (string-length int) 1)) #\.)
            (substring int 0 (- (string-length int) 1))                  ; dalla prima alla penultima cifra
            (rep-int-rec s (substring int 0 (- (string-length int) 1)))
))))

(define rep-rat
  (lambda (s)
    (if (string=? s "")
        ""
        (if (char=? (string-ref s 0) #\.)               ; se il primo carattere è un punto
            (substring s 1 (string-length s))           ; restituisce tutto tranne la prima cifra
            (rep-rat (substring s 1 (string-length s))) ; ricorsione
            )
)))




(define chr-dec
  (lambda (chr seq)               ; chr: carattere, seq: sequenza di riferimento (base della rappresentazione)
    (chr-dec-rec chr seq (string-length seq))
    ))

(define chr-dec-rec
  (lambda (chr seq sql)                            ; chr: carattere, seq: sequenza di ricerca, sql: lunghezza sequenza
    (if (string=? seq "")                          ; se la sequenza è vuota
        0                                          ; restituisce 0
        (if (char=? chr (string-ref seq 0))        ; sennò, se il carattere è uguale al primo simbolo della sequenza
            (- sql (string-length seq))
            (chr-dec-rec chr (substring seq 1) sql))
        )))

(define int-dec
  (lambda (s seq) ; s: stringa parte intera, ; seq: sequenza di riferimento
    (if (string=? s "")
        0
        (+ (* (expt (string-length seq) (- (string-length s) 1))
              (chr-dec (string-ref s 0) seq))
           (int-dec (substring s 1) seq))
       )))

(define rat-dec
  (lambda (s seq) ; s: stringa parte intera, seq: sequenza di riferimento
    (rat-dec-rec (list->string (reverse (string->list s))) seq)
    ))

(define rat-dec-rec
  (lambda (s seq) ; s: stringa parte intera, seq: sequenza di riferimento
    (if (string=? s "")
        0
        (+ (* (expt (string-length seq) (- (string-length s)))
              (chr-dec (string-ref s 0) seq)
              )
           (rat-dec-rec (substring s 1) seq))
        )))

(define bin-rep->number
  (lambda (s)
    (rep->number "01" s)))

(define rep->number
  (lambda (seq s)
    (* (rep-sign s)
       (+ (int-dec (rep-int s) seq)
          (rat-dec (rep-rat s) seq)
          ))))
