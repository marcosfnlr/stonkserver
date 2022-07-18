(defproject stonkserver "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 ; Compojure - A basic routing library
                 [compojure "1.7.0"]
                 ; Our Http library for client/server
                 [http-kit "2.6.0"]
                 ; Ring defaults - for query params etc
                 [ring/ring-defaults "0.3.3"]
                 [ring/ring-json "0.5.1"]
                 ; Clojure data.JSON library
                 [org.clojure/data.json "2.4.0"]
                 [com.google.guava/guava "31.1-jre"]]
  :main ^:skip-aot stonkserver.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
