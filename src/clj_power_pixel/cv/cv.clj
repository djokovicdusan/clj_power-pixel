(ns clj-power-pixel.cv.cv
  (:require [clojure.java.io :as io]
            [clj-power-pixel.metadata :refer :all])
  (:import [org.opencv.core Core Mat]
           [org.opencv.highgui Highgui]
           [org.opencv.imgproc Imgproc]
           [org.opencv.core CvException CvException]
           [com.drew.imaging ImageProcessingException]
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

(defn dimensions-difference
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
        [cols rows] (dimensions-difference first-mat-object second-mat-object)]
    (cond
      (and (> cols 0) (> rows 0)) [second-mat-object first-mat-object]
      (and (<= cols 0) (<= rows 0)) [first-mat-object second-mat-object]
      :else [nil nil])))


(defn match-template-wrapper [source template]
  (let [[rows cols] (dimensions-difference source template)
        comparison-result-mat (Mat. (inc (Math/abs rows)) (inc (Math/abs cols)) (.type source))]
    ;; TM-CCOEFF-NORMED - correlation coefficient method to compare these photos
    ;;TM_CCOEFF method is simply used to make the template and image zero mean and
    ;;  make the dark parts of the image negative values and the bright parts of the image positive values.
    (Imgproc/matchTemplate source template comparison-result-mat Imgproc/TM_CCOEFF_NORMED)
    comparison-result-mat))

(defn get-similiraty-between-two-photos-in-percents [first-mat-object second-mat-object]
  (let [result-mat (match-template-wrapper first-mat-object second-mat-object)
        min-max (Core/minMaxLoc result-mat)
        highest-match-pixel-location (.maxLoc min-max)
        x (.x highest-match-pixel-location)
        y (.y highest-match-pixel-location)
        highest-match-value (.maxVal min-max)
        similarity (* highest-match-value 100)
        match? (> similarity 92)]
    {:x x :y y :match? match? :similarity similarity}))

(defn horizontal-flip
  [mat-object]
  (let [{:keys [rows cols type]} (get-metadata-from-photo-as-mat-object mat-object)
        result-mat (Mat. rows cols type)]
    (Core/flip mat-object result-mat 1)                     ;; third parametar is flipCode - which is 1 for hor. flip, 0 for vertical flip, see doc
    result-mat))

(defn vertical-flip
  [mat-object]
  (let [{:keys [rows cols type]} (get-metadata-from-photo-as-mat-object mat-object)
        result-mat (Mat. rows cols type)]
    (Core/flip mat-object result-mat 0)                     ;; third parametar is flipCode - which is 1 for hor. flip, 0 for vertical flip, see doc
    result-mat))

(defn best-match
  [first-photo-path second-photo-path]
  (let [[source template] (find-if-comparable first-photo-path second-photo-path)]
    (if (and source template)
      (let [match-result-original (get-similiraty-between-two-photos-in-percents source template)
            a-match? (:match? match-result-original)
            match-result-flipped (when-not a-match?
                                   (get-similiraty-between-two-photos-in-percents (horizontal-flip source) template))]
        (if a-match?
          match-result-original
          (let [first-similiraty (:similarity match-result-original)
                second-similarity (:similarity match-result-flipped)]
            (if (> first-similiraty second-similarity)
              match-result-original
              match-result-flipped))))
      {:x 0 :y 0 :match? false :similarity 0})))

(defn safe-best-match
  [first-photo second-photo]
  (try (best-match first-photo second-photo)
       (catch CvException _ {})))
