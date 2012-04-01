(ns problems.core
  (:require [clojure.test :as ct]) )

(load "permutations")

(defn -main [& args]
  (let [s "abcdefgh"
        l (.length s)
        expected-perms (reduce * (range 1 (inc l))) ]
	;;Takes 2 forms a var and a num of times.
    (dotimes [n 5]
      (println)
      (println (str  "#" n))
      ;;thread s into the 1st arg of permutations by inject.
      ;;  then, thread the result into "count", which gives # of perms. 
      ;;	print the result
      ;;	 and finally thread the entire clojure into the timer function, so that
      ;;		when it is done running, the time will be printed out. 
      (time (-> s permutations-by-inject-recursive count println)) 
      (time (-> s permutations-by-inject-iterative count println))
      (time (-> s permutations-by-swap-iterative count println))
      )))
