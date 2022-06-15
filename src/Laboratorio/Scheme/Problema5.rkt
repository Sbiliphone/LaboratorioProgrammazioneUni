;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema5) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define manhattan-3d                       ; val: intero
  (lambda (i j k)                          ; i, j, k: interi non negativi i= altezza griglia, j= larghezza griglia, k= profondità griglia
    (cond ((and (= j 0) (= i 0)) 1)
          ((and (= j 0) (= k 0)) 1)
          ((and (= i 0) (= k 0)) 1)
          ((= k 0) (+ (manhattan-3d i (- j 1) 0) (manhattan-3d (- i 1) j 0))) 
          ((= j 0) (+ (manhattan-3d i 0 (- k 1)) (manhattan-3d (- i 1) 0 k)))
          (else
        (+ (manhattan-3d i (- j 1) k) (manhattan-3d (- i 1) j k) (manhattan-3d i j (- k 1)))
        )
     )))