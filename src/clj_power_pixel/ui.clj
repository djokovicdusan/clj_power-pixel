(ns clj-power-pixel.ui
  (:require [clj-power-pixel.cv.cv :as nscv]
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
                       :id :src :text "Choose source directory") "span 4"]
            [(ss/label :icon (io/resource "icons/ic_folder.png")
                       :id :target :text "Choose target directory") "span 4"]
            [(ss/checkbox :id :copy :selected? true) "span 1"]
            [(ss/label :text "Copy files?") "span 3"]
            [(ss/checkbox :id :cv :selected? true) "span 1"]
            [(ss/label :text "Run plagiarism checker?") "span 3"]
            [""]
            [(ss/button :id :submit :text "Submit" :enabled? false)]]))

(defn build-main-ui-frame
  [exit?]
  (ss/frame :title "Power Pixel tool by Dule Djo"
            :size [500 :by 300]
            :on-close (if exit? :exit :dispose)
            :content my-view
            :resizable? false))

(defn ui-main
  [exit?]
  (ss/invoke-later                                          ;;call on ui thread
    (-> (build-main-ui-frame exit?)
        ss/show!)))

(defn main
  []
  ;;replace with false if you don't want to kill REPL on exit
  (ui-main true))
