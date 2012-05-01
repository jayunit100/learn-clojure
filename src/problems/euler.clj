(ns problems.euler)

(comment
  Add all the natural numbers 
  below one thousand 
  that are multiples of 3 or 5.)
(defn e1 [] 
   (filter #(or 
              (= 0 (mod % 3))
              (= 0 (mod % 5))) 
           (range 1 1000)))
(print (e1))

(comment 
  Generate fibonaccis going forward, rather than 
  using a suboptimal recursion) 
(defn e2 [end]
   (loop [[x y] [0 1] curr 0]
     (cond 
       (= curr end) y
       :else (recur [y (+ x y)] (inc curr)))))