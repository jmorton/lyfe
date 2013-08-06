(ns lyfe.core
  (:require [clojure.set :as s]))

(defn surrounding [point]
  "Obtain all possible points around given point."
  (let [[x y] point
        xs (range (- x 1) (+ x 2))
        ys (range (- y 1) (+ y 2))
        ps (set (for [x xs y ys] (vector x y)))]
    ; don't include the point itself
    (s/difference ps #{point})))

(defn neighbors [point life]
  "Obtain all living points around a given point."
  (s/intersection (surrounding point) life))

(defn happy [point life]
  "Determine if a point ought to be happy."
  (<= 2 (count (neighbors point life)) 3))

(defn keep [life]
  "Use the rules of happiness to determine surviving points."
  (set (filter #(happy % life) life)))

(defn neighborhood [life]
  "Map each point to the number living points around it."
  (frequencies
   (reduce into
     (map seq (map surrounding life)))))

(defn seed [life]
  "Determine set of points ideally suited for life; points with
   exactly three neighbors."
  (let [three-nearby (map key (filter #(= 3 (val %)) (neighborhood life)))]
    (set three-nearby)))

(defn step [life]
  (into (seed life) (keep life)))