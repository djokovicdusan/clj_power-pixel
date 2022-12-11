(ns clj-power-pixel.files
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clj-power-pixel.cv.cv :as cv]
            [clojure.math.combinatorics :as combo]))


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

(defn perform-single-match
  [[file-a file-b]]
  (println ".")
  [(cv/safe-best-match-2 file-a file-b) file-a file-b])

(defn run-pairings
  []
  (let [photos-path-list (find-image-files-and-return-path-list "resources/photos")
        pairings (combo/combinations photos-path-list 2)]
    (pmap perform-single-match pairings)))

(defn find-matches
  []
  (let [results (vec (run-pairings))]
    (filter #(:match? (first %)) results)))

(defn -main
  [& args]
  (let [matches (find-matches)]
    (println "Matches found:")
    (doseq [[result file-a file-b] matches]
      (println file-a "and" file-b))
    (shutdown-agents)))
