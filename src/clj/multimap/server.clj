;; Disable Jetty very verbose logging
(System/setProperty "org.eclipse.jetty.util.log.class" "org.eclipse.jetty.util.log.StdErrLog")
(System/setProperty "org.eclipse.jetty.LEVEL" "OFF") ;; log levels here such as: INFO DEBUG

(ns multimap.server
  (:import java.security.Security)
  (:require
   [multimap.handler :refer [handler dev-handler]]
   [config.core :refer [env]]
   [ring.adapter.jetty :refer [run-jetty]]
   [taoensso.timbre :refer [info spy debug]])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (:port env) "3000"))]
    (run-jetty handler {:port port :join? false})))

(defn start-repl! []
  (when (:dev? env)
    (do
      (require
       '[clojure.tools.nrepl.server]
       '[cider.nrepl])
      (let [start-server        (ns-resolve 'clojure.tools.nrepl.server 'start-server)
            cider-nrepl-handler (ns-resolve 'cider.nrepl 'cider-nrepl-handler)]
        (start-server :port 7888 :handler cider-nrepl-handler)
        (info "CIDER nREPL up at port 7888")))))

(defn -main [& args]
  (let [port (Integer/parseInt (or (:port env) "4000"))]
    ;; changing internet connection and AWS dns changes require lowering ttl
    (Security/setProperty "networkaddress.cache.ttl" "60")
    (start-repl!)
    (run-jetty
     (if (:dev? env) dev-handler handler)
     {:port port :join? false})
    (info "Server running on port" port)
    ;; wait forever
    @(promise)))
