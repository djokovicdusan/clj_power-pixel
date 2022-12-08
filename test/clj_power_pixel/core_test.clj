(ns clj-power-pixel.core-test
  (:require [clojure.test :refer :all]
            [clj-power-pixel.core :refer :all]
            [clj-power-pixel.metadata :refer :all]
            [midje.sweet :refer :all])
  (:import [com.drew.imaging ImageMetadataReader ImageProcessingException]))


(midje.sweet/facts "test author"
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

(facts "test camera model"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2111.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2162.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2284.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2298.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2320.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2329.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2333.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/photos/MS__2338.jpg") => "Canon EOS 7D"
       (clj-power-pixel.metadata/get-camera-model-from-photo "resources-test/dummy/json.js") => nil)
(facts "test file type"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2111.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2162.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2284.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2298.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2320.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2329.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2333.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/photos/MS__2338.jpg") => "JPEG"
       (clj-power-pixel.metadata/get-file-type-from-photo "resources-test/dummy/json.js") => nil)

(facts "test get metadata"
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/dummy/json.js") => {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2111.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2162.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2284.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2298.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2320.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2329.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2333.jpg") =>  seq{}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2338.jpg") =>  seq{})

(facts "test get files"
       (clj-power-pixel.files/find-files-in-given-directory-without-subdirs "resources-test/photos") => seq {})

