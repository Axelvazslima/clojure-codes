(ns clojure-codes.implementing-a-high-order-function)

(defn add
  [acc cur function-factor]
  (let [factor (function-factor cur)]
  (+ acc factor)))


; It will eventually overflow due to non-optimized recursion.
(defn operator-logic
  [sq acc function-factor]
    (if sq
      (operator-logic (next sq) (add acc (first sq) function-factor) function-factor)
    acc))

(defn add-cubes
  [sq]
  (operator-logic sq 0 (fn [x] (* x x x))))

(defn add-doubles
  [sq]
  (operator-logic sq 0 (fn [x] (* x 2))))

(defn add-triples
  [sq]
  (operator-logic sq 0 (fn [x] (* x 3))))

(defn operator
  [sq]
  (operator-logic sq 0 (fn [x] (* x 1))))

(assert (= (add-cubes '(1 3)) 10))
(assert (= (add-doubles '(1 3)) 8))
(assert (= (add-triples '(1 3)) 12))
(assert (= (add-doubles '(22 6 3)) 62))
(assert (= (operator '(1 2 3 4)) 10))
(assert (= (operator '(10 20 30 40)) 100))
(assert (= (operator '(100 200 300 400)) 1000))
(assert (= (operator '(-32 22 3 6 50)) 49))