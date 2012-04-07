(ns problems.puzzles
  (:require [clojure.string :as cs]))

;;To use, simply call (load-file "src/problems/core.clj") from the repl.
 
;;Given a historgram of heights, find a rectangle with max volume that 
;;remains bounded under the histogram.
(def *histogram* '(6 7 0 0))

(comment
       This historgram demonstrates
       that we dont need the whole width
       in order to have max area
       xxxxxxxxxx
      x          x
     x           x  
    x            x      
   x               xxx  
  x                   x                 
       n         e 
)

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


