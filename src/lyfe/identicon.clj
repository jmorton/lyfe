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

(defn color [img point]
  (.getRGB img (point 0) (point 1)))

(defn seed [img]
  (let [steps  (range 0 350 70)
        points (for [x steps y steps] (vector x y))]
    (filter #(filled (color img %)) points)))