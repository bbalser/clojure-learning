(ns clojure-learning.vm
  (:gen-class))

(def COINS {:QUARTER 25 :DIME 10 :NICKEL 5})

(defn- to-keywords [coll]
  (map (partial keyword) coll))

(defn new []
  (ref {:total 0 :coin-return '() :message nil}))

(defn- determine-coin-value [coin]
  ((keyword coin) COINS))

(defn- valid [coin]
  (cond
    (contains? COINS (keyword coin)) :valid
    :else :invalid))

(defn- commit [instance function]
  (dosync (alter instance function)))

(defn- update-state [total invalid-coins]
  (fn [state]
    (-> (update state :total (partial + total))
        (update :coin-return (partial concat invalid-coins)))))

(defn insert [instance coins]
  (let [{valid-coins :valid invalid-coins :invalid} (group-by (partial valid) coins)
        coin-total (reduce + (map determine-coin-value valid-coins))]
    (commit instance (update-state coin-total invalid-coins))))

(defn display [instance]
  (let [{total :total message :message} @instance]
    (cond
      (some? message) (do (commit instance (fn [state] (assoc state :message nil))) message)
      (= 0 total) "INSERT COIN"
      :else (format "%.2f" (double (/ total 100))))))

(defn coin-return [instance]
  (:coin-return @instance))

(defn- complete-purchase [instance price]
  (commit instance (fn [state] (assoc state :total (- (:total @instance) price) :message "THANK YOU"))))

(defn cola [instance]
  (let [total (:total @instance)]
    (cond
      (>= total 100) (do (complete-purchase instance 100) :cola)
      :else (do (commit instance (fn [state] (assoc state :message "PRICE 1.00"))) nil))))

(defn candy [instance]
  (let [total (:total @instance)]
    (cond
      (>= total 65) (do (complete-purchase instance 65) :candy)
      :else (do (commit instance (fn [state] (assoc state :message "PRICE 0.65"))) nil))))
