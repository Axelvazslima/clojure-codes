(ns clojure-codes.length)

(defn length-counter
  [sq counter]
  (if sq
    (length-counter (next sq) (inc counter))
    counter))

(defn length
  [sq]
  (length-counter sq 0))

(def lst (list 1 2 3 4))

(assert (= (length lst) 4))
