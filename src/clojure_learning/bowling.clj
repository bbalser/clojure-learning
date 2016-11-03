(ns clojure-learning.bowling
  (:gen-class))

(defn- parse-int [s]
  (Integer/parseInt s))

(defn- strike? [s]
  (= "X" s))

(defn- gutter? [s]
  (= "-" s))

(defn- spare? [s]
  (= "/" s))

(defn- convert [total next]
  (cond
    (strike? next) (+ total 10)
    (spare? next) (int (* 10 (Math/ceil (/ (+ total 1) 10))))
    (gutter? next) total
    :else (+ total (parse-int next))))

(defn- score-frame [frame]
  (reduce convert 0 frame))

(defn- determine-take-drop [rolls]
  (cond
    (strike? (first rolls)) [3 1]
    (spare? (second rolls)) [3 2]
    :else [2 2]))

(defn- create-frames [rolls]
  (cond
    (= 0 (count rolls)) '()
    :else
      (let [[to-take to-drop] (determine-take-drop rolls)]
        (cons (take to-take rolls) (create-frames (drop to-drop rolls))))))

(defn score [game]
    (->> (clojure.string/split game #"")
         (create-frames)
         (take 10)
         (map score-frame)
         (reduce +)))
