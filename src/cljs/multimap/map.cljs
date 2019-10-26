(ns multimap.map
  (:require
   [crate.core :as crate]
   [mapbox-gl]
   [multimap.config :as config]
   [oops.core :refer [oget oset!]]
   [re-frame.core :as r]
   [reagent.core :as re]
   [taoensso.timbre :refer-macros [spy debug]]
   ))

(def token config/mapbox-public-token)

(def Marker (oget mapbox-gl "Marker"))

(def map-ref (atom nil))

(def options
  {:container "map"
   :style     "mapbox://styles/akaivola/ck0v64jdp0eet1cqu0lpfq38g"
   :center    [24.938976620569917
               60.240800940396426]
   :zoom      10.759965038721813})

(def mapant
  {:id "mapant"
   :source
   {:type "raster"
    :tiles ["https://wmts.mapant.fi/wmts_EPSG3857.php?z={z}&x={x}&y={y}"]
    :tileSize 256}
   :type "raster"
   :layout {:visibility "visible"}
   :source-layer "MapAnt"
   :paint {:raster-opacity 0.3
           }})

(defn gl-map []
  (re/create-class
    {:component-did-mount
     (fn []
       (oset!
         mapbox-gl
         "accessToken"
         token)

       (let [Map   (oget mapbox-gl "Map")
             glmap (Map.
                     (clj->js
                       options))]
         (.on
           glmap
           "load"
           (fn []
             (reset! map-ref glmap)
             (oset! js/window "map" glmap)
             (doto glmap
               (.addLayer (clj->js mapant)
                          (some->>
                              glmap .getStyle .-layers
                              (filter #(= "background" (.-type %)))
                              first
                              .-id)))

             (.on glmap "click"
                  (fn [e]
                    (debug [(oget e "lngLat" "lng") (oget e "lngLat" "lat")])))))
))
     :component-will-unmount (fn [])
     :reagent-render
     (fn []
       [:div#map])}))

(defn map-panel []
  [gl-map])
