;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema7) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define belong? ; dati un intero x e una lista ordinata S, verifica se x è un elemento di S
  (lambda (x S) ; x: int, S: list ord
    (cond
      ((null? S) #false) 
      ((= x (car S)) #true) 
      (else
       (belong? x (cdr S)) 
       ))
))

(define position ; dati un intero x e una lista ordinata e senza ripetizioni S, restituisce la posizione (indice) di x in S.
  (lambda(x S) ; x: int, S: list ord
    (if(belong? x S) 
       (if(= x (car S)) 
          0
          (+ 1 (position x (cdr S))) )
       "Elemento non in lista" 
) ))


(define sorted-ins ; dati un intero x e una lista ordinata e senza ripetizioni S, restituisce la lista ordinata e senza ripetizioni che contine x e tutti gli elementi di S.
  (lambda(x S) ; x: int, S: list
    (cond
      ((null? S) (list x)) 
      ((= x (car S)) (sorted-ins (car S) (cdr S))) 
      ((< x (car S)) (cons x S)) 
      (else 
       (cons (car S) (sorted-ins x (cdr S)))
       )
)))

(define sorted-list ; data una lista senza ripetizioni S, restituisce la lista ordinata e senza ripetizioni S' che contine tutti e soli gli elementi di S.
  (lambda (S) ; S: list
    (cond
    ((null? S) "Lista Vuota") 
    ((null? (cdr S)) S) 
    (else
(sorted-ins (car S) (sorted-list (cdr S))) 
)
)))