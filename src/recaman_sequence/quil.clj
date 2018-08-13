(ns recaman-sequence.quil
  (:require [quil.core :as q]
            [recaman-sequence.core :as rec]
            )
  (:import
    [java.lang Math]))

(def width 1600)
(def height 800)
(def half-height (/ height 2))

(def size [width height])
(def scale-factor 10)
(def PI q/PI)
(def PI2 (* 2 q/PI))


(defn scale [v]
  (* scale-factor v))

(defn draw-semi-circle [center-y start-x end-x orientation]
  (let [center-x (-> (+ start-x end-x) (/ 2))
        radius (Math/abs (- end-x center-x))


        [start end] (if (= orientation :up)
                      [PI PI2]
                      [0 PI])]
    (println "radius" radius "start" start-x center-x end-x)
    (q/arc center-x center-y (* 2 radius) (* 2 radius) start end :open)))

(defn render-number-line [n]
  (q/line 0 half-height width half-height)
  (doseq [i (->> (range 0 n) (map scale))]
    (println i)
    (q/line i half-height i (+ 10 half-height))))

(defn render-circles [n]
  (->>
    (rec/recaman-sequence n)
    (partition 2 1)
    (map-indexed
      (fn [n [start end]]

        (let [direction (if (even? n) :up :down)]
          (println "n => " n "from " start "-" end direction)
          (draw-semi-circle 400 (scale start) (scale end) direction))))
    doall))

(defn setup []
  (q/background 255)
  (q/no-fill)
  (q/stroke 0)                                              ;sets color black
  (q/stroke-weight 1)

  ;(render-number-line 10)
  (render-circles 62)
  )


(defn draw []
  (println "------ begin draw ------")

  (println "------ end draw ------")
  )

(q/defsketch recaman                                        ;; Define a new sketch named example
             :title "Recaman sequence"                      ;; Set the title of the sketch
             ;:settings #(q/smooth 2)             ;; Turn on anti-aliasing
             :setup setup                                   ;; Specify the setup fn
             ;:draw draw                          ;; Specify the draw fn
             :size size)                                    ;; You struggle to beat the golden ratio
