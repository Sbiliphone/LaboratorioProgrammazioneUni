;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Problema2) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(set-puzzle-shift-step!)

(define croce ; val: figura
  (lambda (smaller-tile larger-tile) ; smaller-tile, larger-tile: costanti
    (glue-tiles
     (glue-tiles
      larger-tile
      (shift-down
       (shift-right (half-turn larger-tile) 2) 1))
     (glue-tiles
      (shift-right smaller-tile 2)
      (shift-down
       (shift-right (half-turn smaller-tile) 2) 5) )
) ))

(define quadrato
  (lambda (smaller-tile larger-tile)
    (glue-tiles
     (glue-tiles
      (glue-tiles (half-turn larger-tile)
                  (shift-down (shift-right larger-tile 2) 1))
      (shift-right (half-turn smaller-tile) 2))
     (shift-right (shift-down smaller-tile 5) 2))
    ))