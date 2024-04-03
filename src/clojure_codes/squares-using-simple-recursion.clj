(defn squares-runner
  [sq new-list]
  (cond
  (empty? sq) new-list
  :else (squares-runner (rest sq) (conj new-list (int (Math/pow (first sq) 2))))))

(defn squares
  [sq]
  (squares-runner sq []))

(defn main
  []
  (println (squares '(1 2 3 4))))

(main)
