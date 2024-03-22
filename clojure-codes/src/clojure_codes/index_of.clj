(ns clojure-codes.index-of)

(defn index-finder
  [sq element index]
  (cond
    (empty? sq) -1
    (= (first sq) element) index
    :else (index-finder (next sq) element (inc index))))

(defn index-of
  [sq element]
  (index-finder sq element 0))

(def lst (list 1 2 3 4))

(assert (= (index-of lst 1) 0))
(assert (= (index-of lst 2) 1))
(assert (= (index-of lst 3) 2))
(assert (= (index-of lst 4) 3))
(assert (= (index-of lst 5) -1))
