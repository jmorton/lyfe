(ns lyfe.core-test
  (:require [clojure.test :refer :all]
            [lyfe.core :refer :all]))

(deftest test-surrounding
  (let [points (surrounding [0 0])]
    (is (= points #{ [-1  1 ]  [ 0  1 ]  [ 1  1 ]
                     [-1  0 ]            [ 1  0 ]
                     [-1 -1 ]  [ 0 -1 ]  [ 1 -1 ] }))))
(deftest test-seed
  (let [world (seed [1,1] { :living #{[0,0] [2,2] [0,2] [2,0]} :barren #{} })]
    (is (= (world :living) #{[0,0] [1,1] [2,2] [0,2] [2,0] } ))
    (is (= (world :barren) #{[0,1] [1,0] [1,2] [2,1] } ))))

(deftest test-neighbors
  (let [points #{ [1,0] [1,1] [1,2] } ]
    (is (= (neighbors [1,1] points)
           #{ [1,0] [1,2] }))))

(deftest test-happy
  (let [points #{ [1,0] [1,1] [1,2] } ]
    (is (= (count (neighbors [1,1] points)) 2))
    (is (= (happy [1,1] points) true))))

(deftest test-reap
  (let [stable #{ [0,0] [1,1] [0,1] [1,0] }
        doomed #{ [0,0] [1,1] } ]
    (is (= (reap stable) stable))
    (is (= (reap doomed) #{}))))

(deftest test-sow
  (let [points #{[0,0] [1,1] [0,2]}]
    (is (= (sow points) #{[0,1]}))))
  
