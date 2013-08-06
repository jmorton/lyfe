(ns lyfe.core
  (:require [clojure.set :as s]))


(defn surrounding [point]
  (let [[x y] point
        xs (range (- x 1) (+ x 2))
        ys (range (- y 1) (+ y 2))
        ps (set (for [x xs y ys] (vector x y)))]
    ; don't include the point itself
    (s/difference ps #{point})))


(defn neighbors [point points]
  (s/intersection (surrounding point) points))


(defn happy [point points]
  (<= 2 (count (neighbors point points)) 3))


(defn ideal [point points]
  (= 3 (count (neighbors point points))))


(defn reap [points]
  (set (filter #(happy % points) points)))


(defn sow [points]
  (let [neighborhood (frequencies (reduce into (map seq (map surrounding points))))
        three-nearby (map key (filter #(= 3 (val %)) neighborhood))]
    (set three-nearby)))


(defn step [points]
  (into (sow points) (reap points)))