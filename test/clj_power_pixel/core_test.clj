(ns clj-power-pixel.core-test
  (:require [clojure.test :refer :all]
            [clj-power-pixel.core :refer :all]
            [clj-power-pixel.metadata :refer :all]
            [clj-power-pixel.files :refer :all]

            [midje.sweet :refer :all])
  (:import [org.opencv.core CvException CvException]))


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
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2111.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2162.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2284.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2298.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2320.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2329.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2333.jpg") => seq {}
       (clj-power-pixel.metadata/safe-get-metadata-from-photo "resources-test/photos/MS__2338.jpg") => seq {})

(facts "test get files"
       (clj-power-pixel.files/find-files-in-given-directory-without-subdirs "resources-test/photos") => seq {})

;; mat objects tests
(facts "test mat objects"
       (clj-power-pixel.files/find-image-files-and-return-path-list "resources-test/photos") => seq {}
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2111.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2162.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2284.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2298.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2320.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2329.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2333.jpg")) => true
       (instance? org.opencv.core.Mat (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2338.jpg")) => true)


(facts "test cv pixels destrucuring rows"
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2111.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2162.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2284.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2298.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2320.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2329.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2333.jpg"))) => 3456
       (:rows (clj-power-pixel.cv.cv/get-metadata-from-photo-as-mat-object (clj-power-pixel.cv.cv/load-photo-as-mat-object "resources-test/photos/MS__2338.jpg"))) => 3456)


(facts "test cv comparison"
       (:match? (get (clj-power-pixel.files/perform-single-match
                       ["resources-test/photos/MS__2111.jpg" "resources/photos/MS__2111a.jpg"]) 0)) => true
       (:match? (get (clj-power-pixel.files/perform-single-match
                       ["resources-test/photos/MS__2111.jpg" "resources-test/photos/MS__2111.jpg"]) 0)) => true
       (:match? (get (clj-power-pixel.files/perform-single-match
                       ["resources-test/photos/MS__2111.jpg" "resources-test/photos/MS__2329.jpg"]) 0)) => false)

(facts "test open cv errors"
       (clj-power-pixel.cv.cv/safe-best-match-2 "resources-test/photos/MS__2111.jpg" "resources-test/photos/MS__2111a.jpg") => {})

