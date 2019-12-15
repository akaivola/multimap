(ns multimap.gpx)

(defn- domparse [string]
  (.parseFromString (js/DomParser.) string "text/xml"))

(def gpx (.-gpx js/toGeoJSON))

(defn to-geo-json [string]
  (-> string domparse gpx))
