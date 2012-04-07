(ns problems.puzzles
  (:require [clojure.string :as cs]))

;;Calculate the max area under a histogram if we 
;;have to draw a rectangle which is bounded by all the 
;;points.

; A function wrapper that logs the call.  
(defmacro log [fn-name args & body]
        `(defn ~fn-name ~args
           (println "call func -> ..." ~fn-name ~args )
           ~@body))

;Returns the number of consecutive elements which 
;are >= the compare input.
(log num-ge [compare hist]
    (if (= hist '())
      0
      (if (<= compare (first hist))
          (inc (num-ge compare (rest hist))) 
           0)))


;Higher performance num-ge ... this time, 
;we use tail recursion so the stack is stable.
(log num-geP [compare hist]
    (loop [c compare h hist curr 0]
     ; (print c " " h " " curr "\n")
      (if (= h '())
       curr
       (if (not (<= compare (first h)))
           curr
           (recur compare (rest h) (inc curr) )))))


