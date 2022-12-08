(ns clj-power-pixel.cv.cv
  (:require [clojure.java.io :as io])
  (:import [org.opencv.core Core Mat]
           [org.opencv.highgui Highgui]
           [org.opencv.imgproc Imgproc]
           (clojure.lang RT)))

(defonce opencv-native-loaded?
         (RT/loadLibrary Core/NATIVE_LIBRARY_NAME))

(defn load-image
  [path]
  (Highgui/imread path))

(defn get-image-meta
  [mat]
  (let [rows (.rows mat)
        cols (.cols mat)
        type (.type mat)]
    {:rows rows :cols cols :type type}))

