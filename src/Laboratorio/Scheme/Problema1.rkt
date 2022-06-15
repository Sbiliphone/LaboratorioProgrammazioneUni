;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))

(define soggetto ; val: stringa
  (lambda (x) ; x: stringa
    (string-append
     (if
      (string=?
       (substring x (- (string-length x) 1) (string-length x)) "a") "la "
     (if
      (string=?
       (substring x (- (string-length x) 1) (string-length x)) "o") "il "
    (if
     (string=?
      (substring x (- (string-length x) 1) (string-length x)) "e") "le " "i ")))
     x)
    ))

(define pvs ; val: stringa
  (lambda (vs) ; vs: stringa
    (if
  (string=?
   (substring vs (- (string-length vs) 3) (string-length vs)) "are") (string-append (substring vs 0 (- (string-length vs) 3)) "a")
  (string-append (substring vs 0 (- (string-length vs) 3)) "e")) ))

(define pvp ; val: stringa
  (lambda (vp) ; vp: stringa
  (if
   (string=?
    (substring vp (- (string-length vp) 3) (string-length vp)) "are") (string-append (substring vp 0 (- (string-length vp) 3)) "ano") (string-append (substring vp 0 (- (string-length vp) 3)) "ono"))
  ))

(define co ; val: stringa
  (lambda (c) ; c: stringa
    (string-append
    (if
     (string=?
      (substring c (- (string-length c) 1) (string-length c)) "a") "la "
    (if
     (string=?
      (substring c (- (string-length c) 1) (string-length c)) "o") "il "
    (if
     (string=?
      (substring c (- (string-length c) 1) (string-length c)) "e") "le " "i ")))
    c)
   ))

(define frase ; val: stringa
  (lambda(a b c) ;abc:stringa
  (if
   (string=?
    (substring (soggetto a) (- (string-length (soggetto a)) 1) (string-length (soggetto a))) "a")
   (string-append
    (soggetto a) " " (pvs b) " " (co c))
   (if
    (string=?
     (substring (soggetto a) (- (string-length (soggetto a)) 1) (string-length (soggetto a))) "o")
    (string-append
     (soggetto a) " " (pvs b) " " (co c))
    (if
     (string=?
      (substring (soggetto a) (- (string-length (soggetto a)) 1) (string-length (soggetto a))) "e")
     (string-append
      (soggetto a) " " (pvp b) " " (co c))
     (string-append
      (soggetto a) " " (pvp b) " " (co c)))
    ))))
