(ns clojure-codes.fahr-celsius)

(defn calculate-fahr-celsius
  [number]
  (/ (* (- number 32) 5) 9))

(defn fahr-celsius
  [sq]
  (map calculate-fahr-celsius sq))

(fahr-celsius [30 20 39])