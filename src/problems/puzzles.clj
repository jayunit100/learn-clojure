(ns problems.puzzles
  (:require [clojure.string :as cs]))

;;To use, simply call (load-file "src/problems/core.clj") from the repl.
 
;;Given a historgram of heights, find a rectangle with max volume that 
;;remains bounded under the histogram.
(def histogram {1 1 2 2})