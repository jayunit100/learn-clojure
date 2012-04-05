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


;;Alternative of above function. This time, uses when-let.
(defn first-duplicate-char [in]
  (loop [seen #{} remain (seq in)] ; simplify input vars.
  ;;The let family of macros can destructure a list for you. 
  (when-let ;; takes 2 arguments, a binding, and an expression . 
  	  [[head & tail] remain] 
      (if (contains? seen head) 
        head
        ;;if not seen yet, we add the "head" character to the set of "seen" chars.
        ;;and then proceed to recur on the tail.
        (recur (conj seen head) tail)))))