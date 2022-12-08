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
  [mat-object-first mat-object-second]
  (let [a-meta (get-metadata-from-photo-as-mat-object mat-object-first)
        b-meta (get-metadata-from-photo-as-mat-object mat-object-second)]
    [(- (:cols a-meta) (:cols b-meta))
     (- (:rows a-meta) (:rows b-meta))]))