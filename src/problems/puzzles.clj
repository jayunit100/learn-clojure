(ns problems.puzzles
  (:require [clojure.string :as cs])) 

;;Calculate the max area under a histogram if we 
;;have to draw a rectangle which is bounded by all the 
;;points.

; A function wrapper that logs the call, and also 
; runs the pre assertions 
(defmacro log [fn-name args pre & body]
        ;`template
        `(defn ~fn-name ~args ~pre
          ; (println "call func -> ..." ~fn-name ~args)
           ;;~interpret+@splice
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


(defn vec-drop [coll i]
 {:pre [(seqable? coll)] }
     (vec (drop i coll)))

;input   [10 11 31 4] 0
;returns the map of points -> hist values.
(defn rect-points [histogram i]
  (let [
        hist (vec-drop histogram i)
       ; _ (println "\t\nhist vector: " hist " " (hist 0) "\n")
        steps  (num-geP hist (hist 0))
       ; _ (println "\tsteps " steps  " <= ? " (nth hist 0) )
        Xs (range i (+ i steps)) 
       ; _ (println "\trange " Xs)
        Ys hist ;(map #(nth hist %) Xs)
       ; _ (println "\tdomain " Ys)
        r  (zipmap Xs Ys)
       ; _ (println "done\n")
        ]r)
        )

;Returns the areas of all rectangles, drawn left-to-right, 
;such that the rectangle boundary never exceeds the 
;height of the input histogram. 
(defn pick-rect-points [histogram]
  (let [
        all-starts (range 0 (count histogram))
        _ (println "starts = " all-starts)
        vals (map #(vals (rect-points histogram %)) all-starts)
        _ (println "value lists" vals)
        rects (map 
               #(reduce + %) 
                vals)
        _ (println "areas = " rects)
        areas (zipmap all-starts rects) 
        ]
        areas))

;;Run this program by calling this method, for example, 
;;With the following line at the repl : 
;;input histogram = [10 20 30 40 30 20 10] 
;;(calc-max-area [10 20 30 40 30 20 10])
;;You will get the area of the maximum rectangle residing under 
;;the histogram defined by the heights in the input vector.
(defn calc-max-area [histogram]
  (let [areas (pick-rect-points histogram)
        _ (println "Areas -> " areas)
        max-area (reduce max (vals areas))]
        _ (println "All done.  Thank you and goodnight :)")
        max-area)
  )