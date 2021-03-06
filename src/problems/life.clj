(ns problems.life
  (:require [clojure.string]));;should use "only" here! (use '[clojure.data.json :only (read-json json-str)])

;This function determines wether a cell is alive or dead.
;Its a simplified version of the classic life function.
; f: the initial board function (f), 
; x, y (the x/y coordinates)
(defn live [f x y] 
  (let [neighbors
                  (+ 
	                  (max 0 (f x (dec y)))
	                  (max 0 (f x (inc y))) 
	                  (max 0 (f (inc x) y))
	                  (max 0 (f (dec x) y))
	                  (max 0 (f (dec x) (dec y)))
	                  (max 0 (f (inc x) (inc y)))
	                  (max 0 (f (dec x) (inc y)))
	                  (max 0 (f (inc x) (dec y))))]
    (cond
      (= neighbors 0) 0
      (= neighbors 1) 1
      (= neighbors 2) 1
      (= neighbors 3) 0
      :else 0)))

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

;To play, we simply call newboard over and over again. 
;The effect is simply to calculate the gameboard functionally, so 
;the board is recalculated at every call. Next step will be to add a bitcache or something
;of the sort that is decoupled from the calling of the board.  
(defn main1 []
  (print "\n------------\n")
  (printstate boardstart 3)
  (print "\n------------\n")
  (printstate (newboard boardstart) 3)
  (print "\n------------\n"))
  (printstate (newboard (newboard boardstart)) 3)