(ns clojure-learning.vm-test
  (:require [clojure.test :refer :all]
            [clojure-learning.vm :as vm :refer :all]))

(deftest vending-machine-tests
  (testing "Valid Coins"
    (let [instance (vm/new)]
      (is (= "INSERT COIN" (vm/display instance)))
      (vm/insert instance ["NICKEL"])
      (is (= "0.05" (vm/display instance)))
      (vm/insert instance ["DIME"])
      (is (= "0.15" (vm/display instance)))
      (vm/insert instance ["QUARTER"])
      (is (= "0.40" (vm/display instance)))
      (vm/insert instance ["QUARTER" "QUARTER" "DIME"])
      (is (= "1.00" (vm/display instance)))))
  (testing "Invalid Coins"
    (let [instance (vm/new)]
      (vm/insert instance ["PENNY"])
      (is (= "INSERT COIN" (vm/display instance)))
      (is (= ["PENNY"] (vm/coin-return instance)))))
  (testing "Buying a Cola"
    (let [instance (vm/new)]
      (is (nil? (vm/cola instance)))
      (is (= "PRICE 1.00" (vm/display instance)))
      (is (= "INSERT COIN" (vm/display instance)))
      (vm/insert instance ["QUARTER" "QUARTER" "QUARTER" "QUARTER"])
      (is (= "1.00" (vm/display instance)))
      (is (= :cola (vm/cola instance)))
      (is (= "THANK YOU" (vm/display instance)))
      (is (= "INSERT COIN" (vm/display instance)))))
  (testing "Buying candy"
    (let [instance (vm/new)]
      (is (nil? (vm/candy instance)))
      (is (= "PRICE 0.65" (vm/display instance)))
      (is (= "INSERT COIN" (vm/display instance)))
      (vm/insert instance ["QUARTER" "QUARTER" "DIME" "NICKEL"])
      (is (= "0.65" (vm/display instance)))
      (is (= :candy (vm/candy instance)))
      (is (= "THANK YOU" (vm/display instance)))
      (is (= "INSERT COIN" (vm/display instance))))))
