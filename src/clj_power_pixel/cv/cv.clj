(ns clj-power-pixel.cv.cv
  (:require [clojure.java.io :as io])
  (:import [org.opencv.core Core Mat]
           [org.opencv.highgui Highgui]
           [org.opencv.imgproc Imgproc]
           (clojure.lang RT)))

(defonce opencv-native-loaded?
         (RT/loadLibrary Core/NATIVE_LIBRARY_NAME))

(defn load-photo-as-mat-object
  [photo-path]
  (Highgui/imread photo-path))

(defn get-metadata-from-photo-as-mat-object
  [mat-object]
  (let [rows (.rows mat-object)
        cols (.cols mat-object)
        ;;type is an internal opencv key differentiator
        type (.type mat-object)]
    {:rows rows :cols cols :type type}))

(defn calculate-dimensions-difference-in-pixels
  [first-mat-object second-mat-object]
  (let [a-meta (get-metadata-from-photo-as-mat-object first-mat-object)
        b-meta (get-metadata-from-photo-as-mat-object second-mat-object)]
    [(- (:cols a-meta) (:cols b-meta))
     (- (:rows a-meta) (:rows b-meta))]))


(defn find-if-comparable
  ;; fn that finds if two given photos are comparable
  ;; photos are COMPARABLE when their resolutions in pixels are in compliance
  ;; and if they are comparable, function returns source (child photo) and template(parent photo)
  ;;  if they are NOT COMPARABLE - returns nil
  [first-photo second-photo]
  (let [[first-mat-object second-mat-object] (map load-photo-as-mat-object [first-photo second-photo])
        [cols rows] (calculate-dimensions-difference-in-pixels first-mat-object second-mat-object)]
    (cond
      (and (> cols 0) (> rows 0)) [second-mat-object first-mat-object]
      (and (<= cols 0) (<= rows 0)) [first-mat-object second-mat-object]
      :else [nil nil])))


(defn match-template-wrapper [source template]
  (let [[rows cols] (calculate-dimensions-difference-in-pixels source template)
        comparison-result-mat (Mat. (inc (Math/abs rows)) (inc (Math/abs cols)) (.type source))]
    ;; TM-CCOEFF-NORMED - correlation coefficient method to compare these photos
    ;;TM_CCOEFF method is simply used to make the template and image zero mean and
    ;;  make the dark parts of the image negative values and the bright parts of the image positive values.
    (Imgproc/matchTemplate source template comparison-result-mat Imgproc/TM_CCOEFF_NORMED)
    comparison-result-mat))

(defn get-similiraty-between-two-photos-in-percents [a b]
  (let [result-mat (match-template-wrapper a b)
        min-max (Core/minMaxLoc result-mat)
        highest-match-pixel-location (.maxLoc min-max)
        x (.x highest-match-pixel-location)
        y (.y highest-match-pixel-location)
        highest-match-value (.maxVal min-max)
        similarity (* 100 highest-match-value)
        match? (> similarity 92)]
    {:x x :y y :match? match? :similarity similarity}))