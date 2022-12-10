(ns clj-power-pixel.files
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(defn find-files-in-given-directory-without-subdirs
  [file-directory]
  (->> (io/file file-directory)
       file-seq
       (remove #(or (.isDirectory %)
                    (.isHidden %)))))
(defn find-all-files-in-given-directory
  [file-directory]
  (file-seq (io/file file-directory)))

(defn find-image-files-and-return-path-list
  [file-directory]
  (->> (io/file file-directory)
       file-seq
       (map #(.getPath %))
       (filter #(str/ends-with? % ".jpg"))))