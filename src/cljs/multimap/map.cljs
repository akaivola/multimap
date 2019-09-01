(ns multimap.map
  (:require
   [mapbox-gl]
   [re-frame.core :as r]
   [reagent.core :as re]
   [multimap.config :as config]
   [oops.core :refer [oget]]
   [taoensso.timbre :refer-macros [spy debug]]))

(def token config/mapbox-public-token)

(defn map-panel []
  [:div "map"])
