(defn square
  [number]
  (* number number))

(defn squares
  [numbers]
  (map square numbers))

(def numbers (list 1 2 3 4 5 6 7 8))

(defn main
  []
  (println (squares numbers)))

(main)

(assert (= (squares numbers) (list 1 4 9 16 25 36 49 64)))
