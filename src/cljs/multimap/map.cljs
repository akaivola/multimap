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
   :style     "mapbox://styles/mapbox/outdoors-v9"
   :center    [24.938976620569917
               60.240800940396426]
   :zoom      10.759965038721813})

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
             #_(doto glmap
                    (.addLayer (clj->js mapant)))

             (.on glmap "click"
                  (fn [e]
                    (debug [(oget e "lngLat" "lng") (oget e "lngLat" "lat")])))))
))
     :component-will-unmount
     (fn []
       )
     :reagent-render
     (fn []
       [:div#map])}))

(defn map-panel []
  [gl-map])
