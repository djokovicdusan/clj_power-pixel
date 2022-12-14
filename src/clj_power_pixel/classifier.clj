(ns clj-power-pixel.classifier
  (:require [clj-power-pixel.metadata :as metadata]
            [clj-power-pixel.files :as nsfiles]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn arrange-in-target-folder-by-artist
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        artist (map metadata/get-artist-from-photo files)
        prefix (map #((fnil str "_") %) artist)]
    (map #(str/join "/" [target-directory %1 %2]) prefix filenames)))

(defn arrange-in-target-folder-by-camera-model
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        camera-model (map metadata/get-camera-model-from-photo files)
        prefix (map #((fnil str "_") %) camera-model)]
    (map #(str/join "/" [target-directory %1 %2]) prefix filenames)))

(defn arrange-in-target-folder-by-caption
  [target-directory files]
  (let [filenames (map #(.getName %) files)
        captions (map metadata/get-caption-from-photo files)
        prefixes (map #(str/upper-case ((fnil first "_") %)) captions)] ;;edge case - no caption - will be "_" folder
    (map #(str/join "/" [target-directory %1 %2]) prefixes filenames)))


(defn map-src-and-target-directories-with-caption
  [source-directory target-directory]
  (let [files (nsfiles/find-files-in-given-directory-without-subdirs source-directory)
        target-filenames (arrange-in-target-folder-by-caption target-directory files)]
    (zipmap files target-filenames)))

(defn map-src-and-target-directories-with-artist
  [source-directory target-directory]
  (let [files (nsfiles/find-files-in-given-directory-without-subdirs source-directory)
        target-filenames (arrange-in-target-folder-by-artist target-directory files)]
    (zipmap files target-filenames)))

(defn map-src-and-target-directories-with-camera-model
  [source-directory target-directory]
  (let [files (nsfiles/find-files-in-given-directory-without-subdirs source-directory)
        target-filenames (arrange-in-target-folder-by-camera-model target-directory files)]
    (zipmap files target-filenames)))

(defn arrange-photos-by-class
  [source-directory target-directory]
  (doseq [[source-2 target-filename] (map-src-and-target-directories-with-caption source-directory target-directory)]
    (io/make-parents target-filename)
    (io/copy source-2 (io/file target-filename))))

(defn arrange-photos-by-artist
  [source-directory target-directory]
  (doseq [[source-2 target-filename] (map-src-and-target-directories-with-artist source-directory target-directory)]
    (io/make-parents target-filename)
    (io/copy source-2 (io/file target-filename))))

(defn arrange-photos-by-camera-make-model
  [source-directory target-directory]
  (doseq [[source-2 target-filename] (map-src-and-target-directories-with-camera-model source-directory target-directory)]
    (io/make-parents target-filename)
    (io/copy source-2 (io/file target-filename))))
