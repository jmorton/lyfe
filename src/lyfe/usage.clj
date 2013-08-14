(ns lyfe.usage
  (:require [lyfe.core :as core :refer [step]]
            [lyfe.identicon :refer [seed fetch]]))

(def doomed  [ [0,0] ])
(def still   [ [0,1] [1,1] [0,0] [1,0] ])
(def blinker [ [0,0] [1,0] [2,0] ])
(def toad    [ [0,1] [1,1] [2,1] [0,0] [0,1] [0,2] ])

(def world (atom (set (seed (fetch "jmorton")))))

(swap! world step)

