(ns problems.strings
  (:require [clojure.string :as cs]))

;;To use, simply call (load-file "src/problems/core.clj") from the repl.
 


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
          (rest list-current-chars) 
          (conj hset-chars char-n ))
         nil))))