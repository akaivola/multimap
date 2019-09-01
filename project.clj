(defproject multimap "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [compojure "1.5.0"]
                 [yogthos/config "0.8"]
                 [funcool/cuerdas "2.0.5"]
                 [ring "1.4.0"]
                 [com.taoensso/timbre "4.10.0"]]

  :plugins [[lein-less "1.7.5"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj" "src/cljc"]

  :resource-paths ["resources"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :less {:source-paths ["resources/less"]
         :target-path  "resources/public/css/compiled"}

  :aliases {"dev" ["with-profile" "dev"
                   ["pdo"
                    ["run"]
                    ["less" "auto"]]]}

  :profiles
  {:dev
   {:dependencies [[org.clojure/tools.nrepl "0.2.13" :exclusions [org.clojure/clojure]]
                   [org.clojure/tools.namespace "0.2.11"] ]

    :plugins [[lein-pdo "0.1.1"]
              [refactor-nrepl "2.4.1-SNAPSHOT"]
              [cider/cider-nrepl "0.22.0-beta11"]]}
   :prod    {:dependencies []}
   :uberjar {:dependencies   []
             :omit-source    true
             :aot            :all
             :resource-paths ["resources"]}}

  :main         multimap.server
  :aot          [multimap.server]

  :uberjar {:main         multimap.server
            :aot          [multimap.server]
            :uberjar-name "multimap.jar"
            :prep-tasks   ["compile" ["less" "once"]]})
