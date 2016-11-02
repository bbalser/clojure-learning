(ns clojure-learning.roman
  (:gen-class)
  (:require [clojure.string :as str]))

(def romans {:X 10 :V 5 :I 1})

(defn- repeat-str [times string]
  (apply str (repeat times string)))

(defn- create-new-buffer [{total :arabic buffer :buffer} [roman arabic]]
  (-> (quot total arabic)
      (repeat-str (name roman))
      (->> (str buffer))))

(defn- calculate-remainder [{total :arabic} [_ arabic]]
    (mod total arabic))

(defn- process-arabic [sofar roman-entry]
  {:arabic (calculate-remainder sofar roman-entry)
   :buffer (create-new-buffer sofar roman-entry)})

(defn to-roman [arabic]
  (-> (reduce process-arabic {:arabic arabic :buffer ""} romans)
      (:buffer)))

(defn- process-roman-buffer [romans buffer total]
  (if (= (count romans) 0)
    total
    (let [[roman-symbol arabic] (first romans)
          roman (name roman-symbol)]
      (cond
        (str/starts-with? buffer roman) (recur romans (subs buffer 1) (+ total arabic))
        :else (recur (rest romans) buffer total)))))


(defn to-arabic [roman]
  (process-roman-buffer romans roman 0))
