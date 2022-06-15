;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema4) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define normalized-btr
  (lambda (btr)                  ; btr = stringa
    (cond                        ; nel caso la stringa sia vuota ritorno il carattere punto
      ((= (string-length btr) 0) #\.)
      ((string=? (substring btr 0 1) (string #\.)) (normalized-btr (substring btr 1 (string-length btr)))) ; caso in cui la stringa non ha ulteriori #\. allora stampo la stringa
      (else btr)
)))

; procedura che restituisce la cifra meno significativa (ultima di destra)(carattere) oppure zero (#\.) se l'argomento e' la stringa vuota
(define lsd
  (lambda (btr) ; btr = stringa
    (if (= (string-length btr) 0)
        #\.
        (string-ref btr (- (string-length btr) 1) )
)))

; procedura che restituisce la parte che precede l'ultima cifra (stringa) oppure la stringa vuota ("") se l'argomento e' la stringa vuota
(define head
  (lambda (btr)                       ;btr = stringa ;controllo che la stringa sia vuota
    (if (= (string-length btr) 0)
        ""
        (substring btr 0 (- (string-length btr) 1))
        )))

; procedura che calcola la cifra BTR corrispondente (carattere) della rappresentazione della somma
(define btr-digit-sum    ; val: carattere +/./-
  (lambda (u v c)        ;u, v, c: caratteri +/./-
    (cond ((char=? u #\-)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-) ; - - -
                         #\.)
                        ((char=? c #\.) ;--.
                         #\+)
                        ((char=? c #\+) ;--+
                         #\-)))
                 ((char=? v #\.)
                  (cond ((char=? c #\-) ; - . -
                         #\+)
                        ((char=? c #\.) ;-..
                         #\-)
                        ((char=? c #\+) ;-.+
                         #\.)))
                 ((char=? v #\+) ; - + c
                  c)))
          ((char=? u #\.)
           (cond ((char=? v #\-)
                  (cond ((char=? c #\-) ; . - -
                         #\+)
                        ((char=? c #\.) ;.-.
                         #\-)
                        ((char=? c #\+) ;.-+
                         #\.)))
                 ((char=? v #\.) ; . . c
                  c)
                 ((char=? v #\+)
                  (cond ((char=? c #\-) ; . + -
                         #\.)
                        ((char=? c #\.) ;.+.
                         #\+)
                        ((char=? c #\+) ;.++
                         #\-)))))
          ((char=? u #\+)
           (cond ((char=? v #\-) ; + - c
                  c)
                 ((char=? v #\.)
                  (cond ((char=? c #\-) ; + . -
                         #\.)
                        ((char=? c #\.) ;+..
                         #\+)
                        ((char=? c #\+) ;+.+
                         #\-)))
                 ((char=? v #\+)
                  (cond ((char=? c #\-) ; + + -
                         #\+)
                        ((char=? c #\.) ;++.
                         #\-)
                        ((char=? c #\+) ;+++
                         #\.)))))
          )))

;procedura che restituisce il riporto BTR in uscita (carattere) conseguente alla somma delle cifre
(define btr-carry-cond
  (lambda (u v rip) ;u,v,rip = caratteri ;rip = riporto
    (cond
      ((and(char=? u #\-)(char=? v #\+)(char=? rip #\.)) #\.) ; . - = - -> - + .
      ((and(char=? u #\-)(char=? v #\+)(char=? rip #\-)) #\+) ; - - = + -> + + +
      ((and(char=? u #\-)(char=? v #\+)(char=? rip #\+)) #\.) ; + - = . -> . + .
      
      ((and(char=? u #\-)(char=? v #\-)(char=? rip #\.)) #\-) ; . - = - -> - - -
      ((and(char=? u #\-)(char=? v #\-)(char=? rip #\-)) #\.) ; - - = + -> + - .
      ((and(char=? u #\-)(char=? v #\-)(char=? rip #\+)) #\.) ; + - = . -> . - .

      ((and(char=? u #\+)(char=? v #\+)(char=? rip #\.)) #\+) ; . + = + -> + + +
      ((and(char=? u #\+)(char=? v #\+)(char=? rip #\-)) #\.) ; - + = . -> . + .
      ((and(char=? u #\+)(char=? v #\+)(char=? rip #\+)) #\.) ; + + = - -> - + .
      
      ((and(char=? u #\+)(char=? v #\-)(char=? rip #\.)) #\.) ; . + = + -> + - .
      ((and(char=? u #\+)(char=? v #\-)(char=? rip #\-)) #\.) ; - + = . -> . - .
      ((and(char=? u #\+)(char=? v #\-)(char=? rip #\+)) #\-) ; + + = - -> - - -
      
      ((and(char=? u #\+)(char=? v #\.)(char=? rip #\.)) #\.) ; . + = + -> + . .
      ((and(char=? u #\+)(char=? v #\.)(char=? rip #\-)) #\.) ; - + = . -> . . .
      ((and(char=? u #\+)(char=? v #\.)(char=? rip #\+)) #\.) ; + + = - -> - . .
      
      ((and(char=? u #\-)(char=? v #\.)(char=? rip #\.)) #\.) ; . - = - -> - . .
      ((and(char=? u #\-)(char=? v #\.)(char=? rip #\-)) #\-) ; - - = + -> + . .
      ((and(char=? u #\-)(char=? v #\.)(char=? rip #\+)) #\.) ; + - = . -> . . .
      
      ((and(char=? u #\.)(char=? v #\.)(char=? rip #\.)) #\.) ; . . = . -> . . .
      ((and(char=? u #\.)(char=? v #\.)(char=? rip #\-)) #\.) ; . . = . -> . . .
      ((and(char=? u #\.)(char=? v #\.)(char=? rip #\+)) #\.) ; . . = . -> . . .
      
      ((and(char=? u #\.)(char=? v #\-)(char=? rip #\.)) #\.) ; . . = . -> . - .
      ((and(char=? u #\.)(char=? v #\-)(char=? rip #\-)) #\-) ; - . = - -> - - -
      ((and(char=? u #\.)(char=? v #\-)(char=? rip #\+)) #\.) ; + . = + -> + - .
      
      ((and(char=? u #\.)(char=? v #\+)(char=? rip #\.)) #\.) ; . . = . -> . + .
      ((and(char=? u #\.)(char=? v #\+)(char=? rip #\-)) #\.) ; - . = - -> - + .
      ((and(char=? u #\.)(char=? v #\+)(char=? rip #\+)) #\+) ; + . = + -> + + +
      
      (else #\.)
      )
    )
  )

(define btr-carry                   ; val: carattere +/./-
  (lambda (u v c)                   ; u, v, c: caratteri +/./-
    (cond ((char=? c #\-)
           (cond ((char=? u #\-)
                  (cond ((char=? v #\-) ; - - -
                         #\.)
                        ((char=? v #\.) ;--.
                         #\-)
                        ((char=? v #\+) ;--+
                         #\+)))
                 ((char=? u #\.)
                  (cond ((char=? v #\-) ; - . -
                         #\-)
                        ((char=? v #\.) ;-..
                         #\.)
                        ((char=? v #\+) ;-.+
                         #\.)))
                 ((char=? u #\+) ; - + c
                  (cond ((char=? v #\-) ; - . -
                         #\.)
                        ((char=? v #\.) ;-..
                         #\.)
                        ((char=? v #\+) ;-.+
                         #\.)))))
          ((char=? c #\.)
           (cond ((char=? u #\-)
                  (cond ((char=? v #\-) ; . - -
                         #\-)
                        ((char=? v #\.) ;.-.
                         #\.)
                        ((char=? v #\+) ;.-+
                         #\.)))
                 ((char=? u #\.) ; . . c
                  (cond ((char=? v #\-) ; - . -
                         #\.)
                        ((char=? v #\.) ;-..
                         #\.)
                        ((char=? v #\+) ;-.+
                         #\.)))
                 ((char=? u #\+)
                  (cond ((char=? v #\-) ; . + -
                         #\.)
                        ((char=? v #\.) ;.+.
                         #\.)
                        ((char=? v #\+) ;.++
                         #\+)))))
          ((char=? c #\+)
           (cond ((char=? u #\-) ; + - c
                  (cond ((char=? v #\-) ; - . -
                         #\.)
                        ((char=? v #\.) ;-..
                         #\.)
                        ((char=? v #\+) ;-.+
                         #\.)))
                 ((char=? u #\.)
                  (cond ((char=? v #\-) ; + . -
                         #\.)
                        ((char=? v #\.) ;+..
                         #\.)
                        ((char=? v #\+) ;+.+
                         #\+)))
                 ((char=? u #\+)
                  (cond ((char=? v #\-) ; + + -
                         #\-)
                        ((char=? v #\.) ;++.
                         #\.)
                        ((char=? v #\+) ;+++
                         #\.)))))
)))

; procedura che restituisce la rappresentazione BTR della somma inclusiva del riporto
(define btr-carry-sum
  (lambda (btr1 btr2 r) ;btr1, btr2 = stringa ;r = carattere
    (cond ((and (= (string-length btr1) 0) (= (string-length btr2) 0)) (string r))
          ((and (= (string-length btr1) 0) (> (string-length btr2) 0)) (string-append (btr-carry-sum "" (head btr2) (btr-carry (lsd btr1) (lsd btr2) r)) (string(btr-digit-sum (lsd btr1) (lsd btr2) r))))
          ((and (> (string-length btr1) 0) (= (string-length btr2) 0)) (string-append (btr-carry-sum (head btr1) "" (btr-carry (lsd btr1) (lsd btr2) r)) (string(btr-digit-sum (lsd btr1) (lsd btr2) r))))
          (else (string-append (btr-carry-sum (head btr1) (head btr2) (btr-carry (lsd btr1) (lsd btr2) r)) (string (btr-digit-sum (lsd btr1) (lsd btr2) r))))
)))

; procedura che normalizza la stringa e richiama btr-carry per sommare le due stringhe
(define btr-sum
  (lambda (btr1 btr2)
    (normalized-btr (btr-carry-sum btr1 btr2 #\.))
))
