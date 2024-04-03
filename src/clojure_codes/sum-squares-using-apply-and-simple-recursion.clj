(defn squares-runner
  [sq out]
  (cond
    (empty? sq) out
    :else (squares-runner (rest sq) (cons (int (Math/pow (first sq) 2)) out))))

(defn squares
  [sq]
  (squares-runner sq []))

(defn sum-squares
  [sq]
  (apply + (squares sq)))

(defn main
  []
  (println (sum-squares '(1 2 3 4 22 5 6))))

(main)
