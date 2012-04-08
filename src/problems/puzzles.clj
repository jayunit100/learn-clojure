(ns problems.puzzles
  (:require [clojure.string :as cs]) 
   )
  

;;Calculate the max area under a histogram if we 
;;have to draw a rectangle which is bounded by all the 
;;points.

; A function wrapper that logs the call, and also 
; runs the pre assertions 
(defmacro log [fn-name args pre & body]
        `(defn ~fn-name ~args ~pre
           (println "call func -> ..." ~fn-name ~args)
           ~@body))

;The seqable method seems to have moved from core. heres a workaround.    
(defn seqable?
  [x]
  (instance? clojure.lang.Seqable x))

;Returns the number of consecutive elements which 
;are >= the compare input.
(log num-ge [hist compare] 
    {:pre [(number? compare)] }
    (if (= hist '())
      0
      (if (<= compare (first hist))
          (inc (num-ge (rest hist) compare)) 
           0)))

;Higher performance num-ge ... this time, 
;we use tail recursion so the stack is stable.
(log num-geP [hist compare]
    {:pre [(seqable? hist) (number? compare)]}
    (loop [c compare h hist curr 0]
      ; (print c " " h " " curr "\n")
      (if (= h '())
       curr
       (if (not (<= compare (first h)))
           curr
           (recur compare (rest h) (inc curr) )))))

;input [2,3,*3*,10,4,12,5,18,6,1], 2
;returns [3 10, 4 10, 5 10]
(defn rect-points [histogram i]
  (let [
        hist (seq histogram)
        steps  (num-geP hist (nth hist i))
        Xs (range i (+ i steps)) 
        Ys (map #(nth hist %) Xs)
        r  (zipmap Xs Ys)
        ]r)
        )