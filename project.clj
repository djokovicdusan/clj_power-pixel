(defproject clj_power-pixel "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [com.drewnoakes/metadata-extractor "2.18.0"]
                 [seesaw "1.5.0"]]
  :main image-meta.ui
  :uberjar-name "image-meta.jar"
  :source-paths ["src"]
  :profiles {:uberjar {:aot :all}}
  :repl-options {:init-ns clj-power-pixel.core})
