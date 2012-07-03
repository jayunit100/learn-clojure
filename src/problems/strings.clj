(ns problems.strings
  (:require [clojure.string :as cs])
  (:use [problems.histograms])
  (:use [clojure.test]))
;;To use, simply call (load-file "src/problems/core.clj") from the repl.

(defn word-enrichment1
  "input:  a string 'a b b'
   output: a map : {'a' 1 'b' 2}"
  [str_in]
  {:pre [(= (type str_in) (type ""))]}
  (let [all (clojure.string/split str_in #"\b+")]
    (reduce
      #(let [v (%1 %2)]
         (assoc %1 %2
            (if v (inc v) 1) )) {} all))) 

(defn word-enrichment2
  "input:  a string 'a b b'
   output: a map : {'a' 1 'b' 2}"
  [str_in]
  {:pre [(= (type str_in) (type ""))]}
  (let [all (clojure.string/split str_in #"\b+")]
    (reduce
      #(let [v (%1 %2)]
         (assoc %1 %2
            (if v (inc v) 1) )) {} all)))




;;Look through a collection, see if an object is in it.
;;To exemplify the 'or' function, we have a 2nd equivalence conditions ; Either 
;;the toString is the same, or the objects are = using 
;;the standard clojure = operator.
;;Finally, the "some" function is iterating through the entire
;;seq-collection, and checking if any element (represented in the 
;;anonymous function by %) returning a truthy value.
(defn in? [obj-toFind seq-collection] 
  (if (nil? seq-collection)
     false     
     (some #(or (= obj-toFind %) 
                (= (str obj-toFind) (str %))) 
             ;;(= (.toString obj-toFind) (.toString %))) 
           seq-collection)
      )) 

;;Write a function that returns the first non-repetitive
;;character in a string.
;;Bug : return value is evaluated.
(defn first-duplicate-char [str-in]
  	  (loop [list-Rem (seq str-in) 
             set-Seen  #{}]
        (if (some #(= (first list-Rem) %) set-Seen)
          (first list-Rem)       
          (recur  
            (seq (rest list-Rem))
            (conj set-Seen (first list-Rem))))))

;;Optimize the logic in the top,method 
;;removed the "seq" boilerplate, and replace the function with "in?"
(defn first-dup-char [str]
  (loop [list-current-chars (seq str)
         hset-seen-chars #{}]
    (if (in? (first list-current-chars) hset-seen-chars)     
      (first list-current-chars)
      (if (not (empty? (rest list-current-chars))) 
        (recur 
          (rest list-current-chars)
          (conj hset-seen-chars (first list-current-chars)))
        (print "\nending\n")))))

;;Optimize the logic in the top,method 
;;removed the "seq" boilerplate, and replace the function with "in?"
;;use if let to bind the new char and recur OR exit and return nil 
(defn first-dup-char [str]
  (loop [list-cchars (seq str)
         hset-chars #{}]
    (if (in? (first list-cchars) hset-chars)     
      (first list-cchars)
      (if-let [char-n  (first list-cchars)]   
        (recur
          (rest  list-cchars) 
          (conj hset-chars char-n ))
         nil))))

;;A simplified version of the above method, uses cond 
;;Much more clear. 
(defn first-dup-char2 [str]  
  (loop [lst [] str1 (seq str)]
    (cond 
      (= 0 (count str1)) nil       
      (in? (first str1) lst) (first str1)  
      true (recur (conj str1 (first str1)) (rest str1))
    )))

;;Compression burrow-wheelers
;;Note that conj adds to the end of a vector /
;;Beggining of a string. 
(defn rotate [in]
     (conj (vec (rest in)) (first in)))

;;Apply a function to the same list multiple times. 
(defn applyN [seq_in func_op int_N]
  {:pre [(seqable? seq_in) (function? func_op) (number? int_N)]}
  (loop [s seq_in f func_op n int_N]
    ;(print s " " f " " n " \n")
    (if
      (= n 0) s
      (do
        (recur (map f s) f (dec n))))))


