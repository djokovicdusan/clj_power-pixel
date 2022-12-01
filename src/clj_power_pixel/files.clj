(ns clj-power-pixel.files
  (:require [clojure.java.io :as io]))


(defn find-files-in-given-directory-without-subdirs
  [file-directory]
  (->> (io/file file-directory)
       file-seq
       (remove #(or (.isDirectory %)
                    (.isHidden %)))))
(defn find-all-files-in-given-directory
  [file-directory]
  (->> (io/file file-directory)
       file-seq))