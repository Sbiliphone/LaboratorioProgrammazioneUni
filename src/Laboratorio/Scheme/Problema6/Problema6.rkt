;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname Esercizio_6_Tasselazione) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define L-tessellation
  (lambda(n)
    (if (= n 1)
        L-tile
        (let ((Tile L-tile) (Distanza 1))
          (lsum-tessellation Tile n Distanza))
        )
   )
 )

(define lsum-tessellation
  (lambda(Tile n Distanza)
    (if (= n 1)
        Tile
        (lsum-tessellation (Tasselation-n Tile Distanza) (/ n 2) (* Distanza 2))
     )
   )
 )

(define Tasselation-n
  (lambda(n-Tile Dis)
    (glue-tiles
     (glue-tiles
      n-Tile
      (shift-down (quarter-turn-left n-Tile) Dis)
      )
     (glue-tiles
      (shift-right(quarter-turn-right n-Tile)Dis)
      (shift-right(shift-down n-Tile (/ Dis 2))(/ Dis 2))
      )
     )
  )
 )


(L-tessellation 8)