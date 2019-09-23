(ns multimap.views
  (:require
   [re-frame.core :as re-frame]
   [breaking-point.core :as bp]
   [re-pressed.core :as rp]
   [multimap.subs :as subs]
   [multimap.map :refer [map-panel]]
   ))

;; main

(defn- panels [panel-name]
  (case panel-name
    :map-panel [map-panel]
    [:div]))

(defn show-panel [panel-name]
  [panels panel-name])

(defn main-panel []
  (let [active-panel (re-frame/subscribe [::subs/active-panel])]
    [show-panel @active-panel]))
