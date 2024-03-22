(ns clojure-codes.calculator)

(defn read-integers-from-input
  []
  (let [input-line (read-line)
        integer-strings (clojure.string/split input-line #"\s+")
        integers (map #(Integer. %) integer-strings)]
    integers))

(defn contador
  [s operator]
  (reduce operator s))

(defn which-operator
  [operator sq]
  (cond
    (= operator '+) (contador sq +)
    (= operator '-) (contador sq -)
    (= operator '*) (contador sq *)
    (= operator '/) (contador sq /)
    :else "This operator doesn't exist."))

(defn main
  []
  (println "Input a sequence of integers separating them by whitespaces: ")
  (let [sq (read-integers-from-input)]
  (println "Input what operation do you want to do: ")
  (let [operator (read)]
  (println (which-operator operator sq)))))

(main)
