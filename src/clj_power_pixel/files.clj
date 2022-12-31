(ns clj-power-pixel.files
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [clj-power-pixel.cv.cv :as cv]
            [clojure.math.combinatorics :as combo]
            [criterium.core :refer :all]))


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
  [(cv/safe-best-match file-a file-b) file-a file-b])

(defn run-pairings-matching
  [photo-path]
  (let [photos-path-list (find-image-files-and-return-path-list photo-path)
        pairings (combo/combinations photos-path-list 2)]
    (map perform-single-match pairings)))
(defn run-pairings-matching-parallel
  [photo-path]
  (let [photos-path-list (find-image-files-and-return-path-list photo-path)
        pairings (combo/combinations photos-path-list 2)]
    (pmap perform-single-match pairings)))


(defn find-matches
  [photo-path]
  (let [results (vec (run-pairings-matching-parallel photo-path))]
    (filter #(:match? (first %)) results)))
(defn find-matches-slower
  [photo-path]
  (let [results (vec (run-pairings-matching photo-path))]
    (filter #(:match? (first %)) results)))
(defn run-plag-check-slower
  [photo-path]
  (let [matches (find-matches-slower photo-path)]
    (println "Matches found:")
    (doseq [[result file-a file-b] matches]
      (spit (str "resources/reports/matches" (quot (System/currentTimeMillis) 1000) ".txt")
            (str file-a " and " file-b "\n") :append true)
      (println file-a "and" file-b))
    (shutdown-agents)))

(defn run-plag-check
  [photo-path]
  (let [matches (find-matches photo-path)]
    (println "Matches found:")
    (doseq [[result file-a file-b] matches]
      (spit (str "resources/reports/matches" (quot (System/currentTimeMillis) 1000) ".txt")
            (str file-a " and " file-b " with similiraty of: " (result :similarity) "%" "\n") :append true)
      (println file-a "and" file-b))
    (shutdown-agents)))
