(ns clojure-learning.core-test
  (:require [clojure.test :refer :all]
            [clojure-learning.core :refer :all]))

(deftest a-test
  (testing "FizzBuzz"
    (is (= '("1") (fizzbuzz '(1))))
    (is (= '("2") (fizzbuzz '(2))))
    (is (= '("fizz" "fizz" "fizz") (fizzbuzz '(3 6 9))))
    (is (= '("buzz" "buzz" "buzz") (fizzbuzz '(5 10 20))))
    (is (= '("fizzbuzz" "fizzbuzz") (fizzbuzz '(15 30))))))

(deftest b-test
  (testing "Babysitter"
    (is (= 10 (babysitter 5 6 8)))
    (is (= 6 (babysitter 8 9 8)))
    (is (= 12 (babysitter 1 2 8)))
    (is (= 20 (babysitter 5 7 8)))
    (is (= 16 (babysitter 7 9 8)))
    (is (= 12 (babysitter 12 1 8)))))
