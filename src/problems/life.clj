(ns problems.life
  (:require [clojure.string]));;should use "only" here! (use '[clojure.data.json :only (read-json json-str)])

; GOL is a function, which takes 
; a function as input - the function takes an index(x,y) and returns a value
; for example, 1 or 0.
(defn play1 [f] 
  (print (f '(0 0))))

;This function determines wether a cell is alive or dead.
;inputs: 
; f: the initial board function (f), 
; x, y (the x/y coordinates)
(defn live [f x y] 
  (if (> 1
                  (+ (f x (dec y))
                  (f x (inc y)) 
                  (f (inc x) y)
                  (f (dec x) y)
                  (f (dec x) (dec y))
                  (f (inc x) (inc y))
                  (f (dec x) (inc y))
                  (f (inc x) (dec y)))) 1 
    ;else , die.
    0))

;The gameboard is a function with -1's for boundaries.
(defn boardstart [x y]
  (cond 
     (or (> x 2) (> y 2) (> 0 x) (> 0 y)) -1 ; Out of range
     (= 0 x) 1 ; Cells on top are alive.
     :else 0))

;A function that returns board state given the previous function.
(defn newboard [f]
  #(cond 
     (> 0 (f %1 %2)) -1 ; Out of range
     :else (live f %1 %2)))

;Print the current state of the board
(defn printstate [board size]
  (println "starting state dump")  
  (doseq [x (range size) y (range size)] 
      (println x " " y " | " (board x y))))

(defn main1 []
  (print "\n------------\n")
  (printstate 
    (newboard boardstart) 3)
  (print "\n------------\n"))