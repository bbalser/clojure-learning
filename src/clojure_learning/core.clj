(ns clojure-learning.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn- check [num]
  (cond
    (= (mod num 15) 0) "fizzbuzz"
    (= (mod num 3) 0) "fizz"
    (= (mod num 5) 0) "buzz"
    :else (str num)))

(defn fizzbuzz [list]
  (map check list))

(defn- maptime [hour]
  (cond
    (< hour 5) (+ hour 12)
    :else hour))

(defn- rate [bedtime hour]
  (cond
   (= hour bedtime) 6
   (>= hour 12) 12
   :else 10))

(defn babysitter [arrival departure bedtime]
  (let [interval (range (maptime arrival) (maptime departure))
        ratesbyhour (map (partial rate bedtime) interval)]
    (reduce + ratesbyhour)))
