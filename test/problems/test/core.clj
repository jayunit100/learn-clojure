(ns problems.test.core
  (:use [problems.core])
  (:use [clojure.test])
  (:use [problems.strings])
  (:use [problems.puzzles]))


  ;;Confirm that the first duplicate is a
(deftest test-first-duplicate-char
  ;(print (first-duplicate-char "abcaD"))
  (is (= \a (first-duplicate-char "abcaD"))))

;;Benchmark runs the function "funcge".
(defn benchmark-ge [funcge]
  (funcge [2 3 4 11 1] 2))

;;FP example : Benchmark the num-ge function.
(deftest test-numge
  (is (= (benchmark-ge num-ge) 4)))
(deftest test-numgeP
  (is (= (benchmark-ge num-geP) 4)))

;;Now, make sure the performant num-ge works on large sets.
(deftest test-numgeP_1000
  (is (= 998 (num-geP (range 2 1000) 0))))

;;This helper method can catch arg-swaps that lead to cryptic exceptions.
;;So its important to test that it works in both + and - situations.
(deftest test-seqable
  (is 
    (and 
      (= true (seqable? [1 2])) 
      (= true (seqable? '(1 2))) 
      (= false (seqable? 3))
      )
    )
  )