(ns clojure-learning.bowling-test
  (:require [clojure.test :refer :all]
            [clojure-learning.bowling :refer :all]))

(deftest bowling-tests
  (testing "Bowling Scores"
    (is (= 1 (score "1")))
    (is (= 2 (score "2")))
    (is (= 3 (score "12")))
    (is (= 10 (score "X")))
    (is (= 18 (score "X13")))
    (is (= 2 (score "-2")))
    (is (= 22 (score "7/44")))
    (is (= 36 (score "X3/3-")))
    (is (= 16 (score "-/3")))
    (is (= 300 (score "XXXXXXXXXXXX")))))
