(defn square
  [number]
  (* number number))

(defn squares
  [sq]
  (map square sq))

(defn sum-squares-runner
  [sq out]
  (cond
    (empty? sq) out
    :else (sum-squares-runner (rest sq) (+ out (first sq)))))

(defn sum-squares
  [sq]
  (sum-squares-runner (squares sq) 0))

(defn main
  []
  (println (sum-squares '(1 2 3 4))))

(main)
