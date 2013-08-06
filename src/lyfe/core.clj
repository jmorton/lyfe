(ns lyfe.core
  (:require [clojure.set :as s]))


(defn surrounding [point]
  (let [[x y] point
        xs (range (- x 1) (+ x 2))
        ys (range (- y 1) (+ y 2))
        ps (set (for [x xs y ys] (vector x y)))]
    ; don't include the point itself
    (s/difference ps #{point})))


(defn neighbors [point life]
  (s/intersection (surrounding point) life))


(defn happy [point life]
  (<= 2 (count (neighbors point life)) 3))


(defn ideal [point life]
  (= 3 (count (neighbors point life))))


(defn keep [life]
  (set (filter #(happy % life) life)))


(defn seed [life]
  (let [neighborhood (frequencies (reduce into (map seq (map surrounding life))))
        three-nearby (map key (filter #(= 3 (val %)) neighborhood))]
    (set three-nearby)))


(defn step [life]
  (into (seed life) (keep life)))