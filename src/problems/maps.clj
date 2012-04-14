(ns problems.maps
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
           ;;~interpret and @splice (which means not only do we
           ;;evaluate body -- but we "bring" its contents up into the outer list, so 
           ;;that its not a nested list. 
           ~@body))
 
(log repeats [len str]
     ())