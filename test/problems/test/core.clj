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
  (funcge 2 '(2 3 4 11 1)))

;;FP example : Benchmark the num-ge function.
(deftest test-numge
  (is (= 4 (benchmark-ge num-ge ))))

;;FP example : Benchmark the num-ge (performant) function.
(deftest test-numgeP
  (is (= 4 (benchmark-ge num-geP ))))

;;Now, make sure the performant num-ge works on large sets.
(deftest test-numgeP_1000
  (is (= 998 (num-geP 0 (range 2 1000) ))))