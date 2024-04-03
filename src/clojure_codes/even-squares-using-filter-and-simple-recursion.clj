(defn even-squares-runner
  [sq out]
  (cond
  (empty? sq) out
  :else (even-squares-runner (rest sq) (conj out (int (Math/pow (first sq) 2))))))

(defn even-squares
  [sq]
  (even-squares-runner (filter even? sq) []))

(defn main
  []
  (println (even-squares '(1 2 3 4))))

(main)
