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

(defn- format-money [pennies]
  (format "%.2f" (double (/ pennies 100))))

(defn display [instance]
  (let [{total :total message :message} @instance]
    (cond
      (some? message) (do (commit instance (fn [state] (assoc state :message nil))) message)
      (= 0 total) "INSERT COIN"
      :else (format-money total))))

(defn coin-return [instance]
  (:coin-return @instance))

(defn- complete-purchase [instance price]
  (commit instance (fn [state]
                      (assoc state :total (- (:total @instance) price) :message "THANK YOU"))))

(defn- dispense [instance product cost]
  (let [total (:total @instance)
        message (str "PRICE " (format-money cost))]
    (cond
      (>= total cost) (do (complete-purchase instance cost) product)
      :else (do (commit instance (fn [state] (assoc state :message message))) nil))))

(defn cola [instance]
  (dispense instance :cola 100))

(defn candy [instance]
  (dispense instance :candy 65))

(defn chips [instance]
  (dispense instance :chips 50))
