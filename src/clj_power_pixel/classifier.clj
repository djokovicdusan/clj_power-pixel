(ns clj-power-pixel.classifier
  (:require [clj-power-pixel.metadata :as metadata]
            [clj-power-pixel.files :as fs]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn find-target-filenames
  [target-dir files]
  (let [filenames (map #(.getName %) files)
        captions (map metadata/get-caption-from-photo files)
        prefixes (map #(str/upper-case ((fnil first "_") %)) captions)] ;;edge case - no caption - will be "_" folder
    (map #(str/join "/" [target-dir %1 %2]) prefixes filenames)))