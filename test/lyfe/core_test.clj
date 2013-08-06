(ns lyfe.core-test
  (:require [clojure.test :refer :all]
            [lyfe.core :refer :all]))

(deftest test-surrounding
  (let [points (surrounding [0 0])]
    (is (= points #{ [-1  1 ]  [ 0  1 ]  [ 1  1 ]
                     [-1  0 ]            [ 1  0 ]
                     [-1 -1 ]  [ 0 -1 ]  [ 1 -1 ] }))))

(deftest test-neighbors
  (let [points #{ [1,0] [1,1] [1,2] } ]
    (is (= (neighbors [1,1] points)
           #{ [1,0] [1,2] }))))

(deftest test-happy
  (let [points #{ [1,0] [1,1] [1,2] } ]
    (is (= (count (neighbors [1,1] points)) 2))
    (is (= (happy [1,1] points) true))))

(deftest test-keep
  (let [stable #{ [0,0] [1,1] [0,1] [1,0] }
        doomed #{ [0,0] [1,1] } ]
    (is (= (keep stable) stable))
    (is (= (keep doomed) #{}))))

(deftest test-seed
  (let [points #{[0,0] [1,1] [0,2]}]
    (is (= (seed points) #{[0,1]}))))

(deftest test-step
  (let [points #{[0,0] [1,1] [2,0] [2,1] [20,20]}]
    (is (= (step points) #{[1,1] [2,1] [2,0]}))
    (is (= (step (step points)) #{[1,0] [1,1] [2,0] [2,1]}))))
