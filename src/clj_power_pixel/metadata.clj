(ns clj-power-pixel.metadata
  (:require [clojure.java.io :as io])
  (:import [com.drew.imaging ImageMetadataReader ImageProcessingException]))



(defn- get-data-from-tags
  [tags]
  (into {} (map (fn [t] {(.getTagName t) (.getDescription t)}) tags)))

(defn- get-metadata-from-photo
  ;; function that fetches all tags from mapped collections
  ;; , when given the file name
  [photo-path]
  (->> (io/file photo-path)
       ImageMetadataReader/readMetadata
       .getDirectories
       (map #(.getTags %))
       (map get-data-from-tags)
       (into {}))
  )
(defn safe-get-metadata-from-photo
  [photo-path]
  (try (get-metadata-from-photo photo-path)
       (catch ImageProcessingException _ {})))

(defn get-copyright-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Copyright"))

(defn get-artist-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Artist"))

(defn get-focal-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Focal Length"))

(defn get-date-captured-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Date/Time Original"))

(defn get-camera-model-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Model"))

(defn get-lens-model-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Lens Model"))

(defn get-file-type-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Detected File Type Name"))
(defn get-caption-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Caption/Abstract"))

(defn get-rating-from-photo
  [photo-path]
  (get (safe-get-metadata-from-photo photo-path) "Rating"))


