(ns clj-power-pixel.ui
  (:require [clj-power-pixel.cv.cv :as nscv]
            [clj-power-pixel.classifier :as nsclass]
            [clj-power-pixel.files :as nsfiles]
            [seesaw.core :as ss]
            [seesaw.mig :as mig]
            [seesaw.chooser :as sc]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(def my-view
  (mig/mig-panel
    :constraints ["wrap 4, center, gap 15"]
    :items [[(ss/label :icon (io/resource "icons/ic_folder.png")
                       :id :sourceDirectory :text "Choose source directory") "span 4"]
            [(ss/label :icon (io/resource "icons/ic_folder.png")
                       :id :targetDirectory :text "Choose target directory") "span 4"]
            [(ss/checkbox :id :cv :selected? false) "span 1"]
            [(ss/label :text "Run plagiarism checker?") "span 4"]
            [""]
            [(ss/label :text "Classify by:")]
            [(let [group (ss/button-group)]
               (ss/flow-panel :items [(ss/radio :id :camera-button :text "Camera" :group group)
                                      (ss/radio :id :artist-button :text "Artist" :group group)
                                      (ss/radio :id :caption-button :text "Caption" :group group)]))]

            [(ss/button :id :submit :text "Submit" :enabled? false)]]))

(def ui-data (atom {:cv false :camera false :artist false :caption false}))

(defn is-submit-button-enabled
  [frame]
  (let [{:keys [sourceDirectory targetDirectory cv camera artist caption]} @ui-data]
    (when (and sourceDirectory targetDirectory)
      (ss/config! (ss/select frame [:#submit]) :enabled? true))
    (when (and sourceDirectory cv (every? false? [camera artist caption]))
      (ss/config! (ss/select frame [:#submit]) :enabled? true))))

(defn choose-dir
  [frame key]
  (when-let [chosen-dir (sc/choose-file :selection-mode :dirs-only :dir "resources")]
    (let [absolute-path (.getAbsolutePath chosen-dir)
          directory-name (.getName chosen-dir)
          selector-key (if (= key :sourceDirectory) :#sourceDirectory :#targetDirectory)]
      (ss/config! (ss/select frame [selector-key]) :text directory-name)
      (swap! ui-data assoc key absolute-path)
      (is-submit-button-enabled frame))))

(defn on-submit-button-clicked
  [frame]
  (let [{:keys [sourceDirectory targetDirectory cv camera artist caption]} @ui-data]
    (when (and sourceDirectory targetDirectory caption)
      (nsclass/arrange-photos-by-class sourceDirectory targetDirectory))
    (when (and sourceDirectory targetDirectory camera)
      (nsclass/arrange-photos-by-camera-make-model sourceDirectory targetDirectory))
    (when (and sourceDirectory targetDirectory artist)
      (nsclass/arrange-photos-by-artist sourceDirectory targetDirectory))
    (when (and sourceDirectory targetDirectory cv)
      (nsfiles/run-plag-check sourceDirectory))))

(defn add-listeners
  [frame]
  (let [{:keys [sourceDirectory targetDirectory cv camera-button artist-button caption-button submit]} (ss/group-by-id frame)]
    (ss/listen sourceDirectory :mouse-clicked (fn [_] (choose-dir frame :sourceDirectory)))
    (ss/listen targetDirectory :mouse-clicked (fn [_] (choose-dir frame :targetDirectory)))
    (ss/listen cv :mouse-clicked #(swap! ui-data assoc :cv (ss/value %)))
    (ss/listen submit :mouse-clicked (fn [_] (on-submit-button-clicked frame)))
    (ss/listen camera-button :selection #(swap! ui-data assoc :camera (ss/value %)))
    (ss/listen artist-button :selection #(swap! ui-data assoc :artist (ss/value %)))
    (ss/listen caption-button :selection #(swap! ui-data assoc :caption (ss/value %)))
    frame))

(defn build-main-ui-frame
  [exit?]
  (ss/frame :title "Power Pixel by Dule Djo"
            :size [500 :by 300]
            :on-close (if exit? :exit :dispose)
            :content my-view
            :resizable? false))

(defn ui-main
  [exit?]
  (ss/invoke-later
    (-> (build-main-ui-frame exit?)
        ss/show!
        add-listeners)))

(defn -main
  []
  ;;replace with false if you don't want to kill REPL on exit
  (ui-main true))

