(ns clj-power-pixel.core-test
  (:require [clojure.test :refer :all]
            [clj-power-pixel.core :refer :all]
            [clj-power-pixel.metadata :refer :all]
            [midje.sweet :refer :all]))


(facts "test author"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2111.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2162.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2284.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2298.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2320.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2329.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2333.jpg") => "DUSAN DJOKOVIC"
       (clj-power-pixel.metadata/get-artist-from-photo "resources-test/photos/MS__2338.jpg") => "DUSAN DJOKOVIC")

(facts "test caption"
       (clj-power-pixel.metadata/get-caption-from-photo "resources-test/photos/MS__2111.jpg") => "A")

