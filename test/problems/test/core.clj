(ns problems.test.core
  (:use [problems.core])
  (:use [clojure.test])
  (:use [problems.strings])
  (:use [problems.histograms]))


(deftest test-performance-wc
  ;(print (first-duplicate-char "abcaD"))
  (let [txt (slurp "http://www.google.com")]
    (print "time for set based word count\n")
    (time (word-enrichment1 txt))
    (print "time for reducer word count (should be less)\n")
    (time (word-enrichment2 txt)))
  )


  ;;Confirm that the first duplicate is a
(deftest test-first-duplicate-char
  ;(print (first-duplicate-char "abcaD"))
  (is (= \a (first-duplicate-char "abcaD"))))

(deftest test-first-duplicate-char2
  ;(print (first-duplicate-char "abcaD"))
  (is (= \b (first-dup-char2 "abbcD"))))
  
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

(deftest test-program (is (= 160  (calc-max-area [10 20 30 40 30 20 10]))))

; Very cool function that repeatedly does an operation to a list.
; Might work for generating rotations of a string charset
(deftest test-apply (is (= 
                          [4 5 6] 
                          (applyN [1 2 3] #(+ % 3) 1))))

