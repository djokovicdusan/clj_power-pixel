(defproject clj_power-pixel "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.drewnoakes/metadata-extractor "2.18.0"]
                 [seesaw "1.5.0"]
                 [opencv/opencv-native "2.4.13"]
                 [opencv/opencv "2.4.13"]]
  :main clj_power-pixel.files
  :plugins [[lein-localrepo "0.5.2"]]
  :source-paths ["src"]
  :profiles {:uberjar {:aot :all}
             :dev
             {:dependencies [[midje "1.10.9"]]
              :plugins      [[lein-midje "3.2.1"]]}}

  :repl-options {:init-ns clj_power-pixel.files})
