(ns clojure-codes.sum-of-even-numbers-square)

(defn power-two
  [number]
  number * number)

(defn sum-squares-even-numbers
  [sq]
  (map power-two (filter (fn [i] (= (mod i 2) 0)) sq)))

(def ls '(1 2 3 4 5 6 7 8 9 10))
(defn main
  []
  (println (sum-squares-even-numbers ls)))

(main)
