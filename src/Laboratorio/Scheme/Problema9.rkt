;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema9) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
(define alfab-latino "ABCDEFGHILMNOPQRSTVX")

(define posizione
  (lambda (cr alfab) ; cr: carattere, alfab: alfabeto
    (if (char=? cr (string-ref alfab 0))
        0
        (+ (posizione cr (substring alfab 1)) 1)
)))

(define cesare-rec
  (lambda (s) ; s: di quanto si sposta
    (lambda (cr) ; cr: carattere
      (let ([k (+ (posizione cr alfab-latino) s )])
        (if (> k (string-length alfab-latino)) (string-ref alfab-latino (- k 19)) (string-ref alfab-latino k)
)))))

(define H (lambda (f g)
            (lambda (m n)
              (if (= n 0)
                  (f m)
                  (g m ((H f g) m (- n 1))) 
))))

(define succ
  (lambda (u v)
    (+ v 1)
))

(define add
  (H (lambda (x) x) succ)
)

(define mul
  (H (lambda (x) 0) add)
)

(define pow
  (H (lambda (x) 1) mul)
)