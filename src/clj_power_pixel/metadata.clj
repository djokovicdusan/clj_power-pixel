(ns clj-power-pixel.metadata
  (:require [clojure.java.io :as io])
  (:import [com.drew.imaging ImageMetadataReader ImageProcessingException])
  )
(defn- get-metadata-from-photo
  ;; function that fetches all tags from mapped collections
  ;; , when given the file name
  [filename]
  (->> (io/file filename)
       ImageMetadataReader/readMetadata
       .getDirectories
       (map #(.getTags %)))
  )
