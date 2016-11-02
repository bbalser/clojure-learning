(ns clojure-learning.roman-test
  (:require [clojure.test :refer :all]
            [clojure-learning.roman :refer :all]))

(deftest roman
  (testing "To Roman"
    (is (= "I" (to-roman 1)))
    (is (= "II" (to-roman 2)))
    (is (= "V" (to-roman 5)))
    (is (= "VI" (to-roman 6)))
    (is (= "XXX" (to-roman 30)))))


(deftest arabic
  (testing "To Arabic"
    (is (= 1 (to-arabic "I")))
    (is (= 2 (to-arabic "II")))
    (is (= 5 (to-arabic "V")))
    (is (= 6 (to-arabic "VI")))
    (is (= 10 (to-arabic "X")))
    (is (= 26 (to-arabic "XXVI")))))
