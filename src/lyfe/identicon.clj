(ns lyfe.identicon
  (:import (javax.imageio ImageIO)
           (java.net URL)))


(defn fetch [name]
  (let [url (URL. (str "http://identicons.github.com/" name ".png"))
        img (ImageIO/read (.openStream url))
        sub (.getSubimage 35 35 350 350)]
    sub))

(defn filled [color]
  (not= color -986896))

(defn color [img [x y]]
  (.getRGB img (point x) (point y)))

(defn pinpoint [[x y]]
  [ (/ x 70) (/ y 70)])

(defn seed [img]
  (let [steps  (range 0 351 70)
        points (for [x steps y steps] (vector x y))]
    (map pinpoint (filter #(filled (color img %)) points))))
