(ns clojure-codes.collection-mapping)

(defn contador
  [sq operator factor]
  (map (fn [x] (operator factor x)) sq))

(defn main
  []
  (println (contador [10 20 30 40 50] * 2)))

(main)
