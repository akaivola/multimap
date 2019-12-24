(ns multimap.gps
  (:require
   [re-frame.core :as r]))

(defn halt-tracking! [id]
  (.clearWatch
   (.-geolocation js/navigator)
   id))

(defn init-tracking! []
  (when-let [loc (.-geolocation js/navigator)]
    (.watchPosition
     loc
     (fn [position]
       #_(debug "Position accuracy:" (-> position .-coords .-accuracy)
                "Heading:" (-> position .-coords .-heading)
                "Speed:" (-> position .-coords .-speed))
       (r/dispatch [:map/gps
                    {:latitude  (-> position .-coords .-latitude)
                     :longitude (-> position .-coords .-longitude)
                     :accuracy  (some-> position .-coords .-accuracy int)
                     :heading   (some-> position .-coords .-heading int)
                     :type      type
                     :speed     (some-> position .-coords .-speed int)}]))
     (fn [err]
       (.dir js/console err))
     (clj->js
      {:enableHighAccuracy true
       :maximumAge         0 ;; realtime
       :timeout            60000}))))

(r/reg-sub
 :map/gps-state?
 (fn [db [_]]
   (-> @db :map :gps :state :id boolean)))

(r/reg-event-db
 :map/gps
 (fn [db [_ position]]
   (assoc-in @db [:map :gps :position] position)))

(r/reg-event-db
 :map/toggle-gps
 (fn [db _]
   (update-in
    db [:map :gps :state]
    (fn [{:keys [id marker]}]
      (if id
        (do
          (halt-tracking! id)
          (when marker (.remove marker))
          {})
        {:id (init-tracking!)})))))
