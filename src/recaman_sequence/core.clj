(ns recaman-sequence.core)


(defn extend-recaman [cur-sequence]

  (let [n (count cur-sequence)
        prev (last cur-sequence)
        vals (set cur-sequence)
        backwards-candidate (- prev n)]
    (cond
      (and
        (> backwards-candidate 0)
        (not (vals backwards-candidate))) (conj cur-sequence backwards-candidate)
      :else (conj cur-sequence (+ prev n)))))

(defn recaman-sequence [n]
  (loop [cur-n 0
         sequence [0]]
    (if (= (count sequence) n)
      sequence
      (recur (inc cur-n) (extend-recaman sequence)))))

; todo figure out how to make lazy seq
;(defn recaman-lazy
;  ([n] (recaman-lazy n []))
;  ([n cur-seq]
;    (lazy-seq
;      (let [extended (extend-recaman cur-seq)])
;      (cons )))
;  (loop [cur-n 0
;         sequence [0]]
;    (if (= (count sequence) n)
;      sequence
;      (recur (inc cur-n) (extend-recaman sequence)))))
