;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema8) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
(define hanoi-moves ; val: lista di coppie
  (lambda (n) ; n > 0 intero
    (hanoi-rec n 1 2 3)
))

(define hanoi-rec ; val: lista di coppie
  (lambda (n s d t) ; n intero, s, d, t: posizioni
    (if (= n 1)
    (list (list s d))
    (let ((m1 (hanoi-rec (- n 1) s t d))
          (m2 (hanoi-rec (- n 1) t d s))
          )
(append m1 (cons (list s d) m2)) ))
))

(define hanoi-disks
  (lambda (n k)
    (hanoi-disks-rec n k 1 2 3 0 0 0)
    ))

(define hanoi-disks-rec
  (lambda (n k s d t s-disks d-disks t-disks) ; s: prima asticella, d: seconda asticella, t: terza asticella, s-disks: dischi nella prima asticella, ...
    (cond
      ((= n 0) (list (list s s-disks) (list d d-disks) (list t t-disks))) 
      ((= k 0) (list (list s n) (list d d-disks) (list t t-disks)))
      ((< k (expt 2 (- n 1))) (hanoi-disks-rec (- n 1) k s t d (+ s-disks 1) t-disks d-disks)) 
      (else
       (hanoi-disks-rec (- n 1) (- k (expt 2 (- n 1))) t d s t-disks (+ d-disks 1) s-disks)
))))

(define hanoi-picture
  (lambda (n k)
    (hanoi-picture-rec n k 1 2 3 0 0 0 (towers-background n) n) 
    ))



(define hanoi-picture-rec
  (lambda (n k s d t s-disks d-disks t-disks img disks)
    (cond ((= n 0) img ) 
          ((< k (expt 2 (- n 1))) (hanoi-picture-rec (- n 1) k s t d (+ s-disks 1) t-disks d-disks (above (disk-image n disks s s-disks) img) disks))
          (else
           (hanoi-picture-rec (- n 1) (- k (expt 2 (- n 1))) t d s t-disks (+ d-disks 1) s-disks (above (disk-image n disks d d-disks) img) disks)
)
)))