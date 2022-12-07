(ns clj-power-pixel.classifier
  (:require [clj-power-pixel.metadata :as metadata]
            [clj-power-pixel.files :as nsfiles]
            [clojure.string :as str]
            [clojure.java.io :as io]))


(defn find-target-filenames-and-arrange-in-folder-by-copyright
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        copyright (map metadata/get-copyright-from-photo files)]
    (map #(str/join "/" [target-directory %1 %2]) copyright filenames)))

(defn find-target-filenames-and-arrange-in-folder-by-artist
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        artist (map metadata/get-artist-from-photo files)]
    (map #(str/join "/" [target-directory %1 %2]) artist filenames)))

(defn find-target-filenames-and-arrange-in-folder-by-camera-model
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        camera-model (map metadata/get-camera-model-from-photo files)]
    (map #(str/join "/" [target-directory %1 %2]) camera-model filenames)))

(defn find-target-filenames-and-arrange-in-folder-by-class
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        captions (map metadata/get-caption-from-photo files)
        prefixes (map #(str/upper-case ((fnil first "_") %)) captions)] ;;edge case - no caption - will be "_" folder
    (map #(str/join "/" [target-directory %1 %2]) prefixes filenames)))


(defn map-src-and-target-directories-with-caption-classifier
  [source-directory target-directory]
  (let [files (nsfiles/find-files-in-given-directory-without-subdirs source-directory)
        target-filenames (find-target-filenames-and-arrange-in-folder-by-class target-directory files)]
    (zipmap files target-filenames)))

(defn arrange-photos
  [source-directory target-directory]
  (doseq [[source-2 target-filename] (map-src-and-target-directories-with-caption-classifier source-directory target-directory)]
    (io/make-parents target-filename)
    (io/copy source-2 (io/file target-filename))))
