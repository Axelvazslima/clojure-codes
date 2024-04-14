(defn update-table
  [old-table position player-mark]
  (assoc-in old-table position player-mark))

(defn create-table
  []
  [[0, 0, 0]
   [0, 0, 0]
   [0, 0, 0]])

(defn print-updated-table
  [table]
  (doseq [row table]
    (println (clojure.string/join " | " row))
  (println "---------")))

(defn position-getter
  [position table]
  (let [outer-index (Integer/parseInt (subs position 0 1))
        inner-index (Integer/parseInt (subs position 1))]
    (cond
      (or (> outer-index 2) (< outer-index 0) (> inner-index 2) (< inner-index 0) (not= (get-in table [outer-index inner-index]) 0))
      (do
        (println "Wrong input. Try again.")
        (recur (read-line) table))
      :else [outer-index inner-index])))

(defn check-winner-in-column [table column element]
  (let [column-values (map #(get-in % [column]) table)]
    (every? (fn [x] (= x element)) column-values)))

(defn check-winner-in-line [table line element]
  (let [row (nth table line)]
    (every? (fn [x] (= x element)) row)))

(defn check-winner-in-diags [table element]
  (let [left-diag (map-indexed (fn [i row] (get row i)) table)
        right-diag (map-indexed (fn [i row] (get row (- (count table) 1 i))) table)]
    (or (every? (fn [x] (= x element)) left-diag)
         (every? (fn [x] (= x element)) right-diag))))

(defn is-there-a-winner [table line column]
  (let [element (get-in table [line column])
        cond-lines (check-winner-in-line table line element)
        cond-columns (check-winner-in-column table column element)
        cond-diag (check-winner-in-diags table element)]
    (or cond-lines cond-columns cond-diag)))

(defn place-x
  [position old-table]
  (update-table old-table position 1))

(defn place-o
  [position old-table]
  (update-table old-table position 2))

(defn jogo
  []
  (let [table (create-table)]
  (print-updated-table table)
  (loop [counter 0
        continue true
        updated-table table]
  (when continue
    (cond
      (= (mod counter 2) 0)
        (let [player 0]
          (println (format "Player %s:" player))
          (println "Input a position, like (LineColumn: 01): ")
          (def position (position-getter (read-line) updated-table))
          (def new-table (place-x position updated-table)))
      :else
        (let [player 1]
          (println (format "Player %s:" player))
          (println "Input a position, like (LineColumn: 01): ")
          (def position (position-getter (read-line) updated-table))
          (def new-table (place-o position updated-table))))
      (print-updated-table new-table)
      (cond
        (= counter 9) (println "DRAW")
        (is-there-a-winner new-table (nth position 0) (nth position 1)) (println "You won!")
        :else (recur (inc counter) continue new-table))))))

(defn main
  []
  (jogo))

(main)
