(ns clj-power-pixel.benchmark.benchmark
  (:require [criterium.core :refer :all]
            [clj-power-pixel.cv.cv :as nscv]
            [clj-power-pixel.classifier :as nsclass]
            [clj-power-pixel.files :as nsfiles]))


(criterium.core/with-progress-reporting (criterium.core/quick-bench (+ 1 1)))

(time (nsfiles/run-plag-check "resources/images"))
;"Elapsed time: 44244.1797 msecs"

(time (nsfiles/run-plag-check-slower "resources/images"))
;"Elapsed time: 32390.8035 msecs"

; We can conclude that the pmap function did the better job than regular map function,
; in our code, in terms
; of performance
