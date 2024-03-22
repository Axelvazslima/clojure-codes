(ns clojure-codes.value-at)

(defn value-at-finder
  [sq index current-index]
  (cond
    (empty? sq) -1
    (= index current-index) (first sq)
    :else (value-at-finder (next sq) index (inc current-index))))

(defn value-at
  [sq index]
  (value-at-finder sq index 0))

(def lst (list 1 2 3 4))

(assert (= (value-at lst 3) 4))
(assert (= (value-at lst 0) 1))
(assert (= (value-at lst 1) 2))
(assert (= (value-at lst 2) 3))
(assert (= (value-at lst 5) -1))
(assert (= (value-at lst 1000) -1))
(assert (= (value-at lst -1) -1))
(assert (= (value-at lst 10) -1))
