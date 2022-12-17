(ns clj-power-pixel.benchmark.benchmark
  (:require [criterium.core :as crit]
            [clj-power-pixel.cv.cv :as nscv]
            [clj-power-pixel.classifier :as nsclass]
            [clj-power-pixel.files :as nsfiles]))


(crit/with-progress-reporting (crit/quick-bench (+ 1 1)))

;(time ( 1000))