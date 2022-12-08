(ns clj-power-pixel.core-test
  (:require [clojure.test :refer :all]
            [clj-power-pixel.core :refer :all]
            [clj-power-pixel.metadata :refer :all]
            [midje.sweet :refer :all])
  (:import [com.drew.imaging ImageMetadataReader ImageProcessingException]))


(facts "test author"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2111.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2162.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2284.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2298.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2320.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2329.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2333.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2338.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/dummy/json.js") => nil)

(facts "test caption"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2111.jpg") => "A"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2162.jpg") => "A"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2284.jpg") => "B"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2298.jpg") => "C"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2320.jpg") => "A"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2329.jpg") => "B"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2333.jpg") => "C"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2338.jpg") => "A"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/dummy/json.js") => nil)



