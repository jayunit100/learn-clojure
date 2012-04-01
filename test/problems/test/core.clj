(ns problems.test.core
  (:use [problems.core])
  (:use [clojure.test])
  (:use [problems.strings]))

;;Confirm that the first duplicate is a
(deftest test-first-duplicate-char
  (print (first-duplicate-char "abcaD"))
  (is (= \a (first-duplicate-char "abcaD"))))