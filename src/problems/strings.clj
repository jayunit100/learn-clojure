(ns problems.strings
  (:require [clojure.string :as cs]))

;;To use, simply call (load-file "src/problems/core.clj") from the repl.
 

;;Write a function that returns the first non-repetitive
;;character in a string.
;;Bug : return value is evaluated.
(defn first-duplicate-char [str-in]
  	  (loop [list-Rem (seq str-in) set-Seen (set [])]
  	  	(print (type list-Rem) " " list-Rem (rest list-Rem) "\n")
		(if (some #(= (first list-Rem) %) set-Seen)
	    	(first list-Rem)
    	 		(recur  
     				(seq (rest list-Rem))
					(conj set-Seen (first list-Rem))))))


