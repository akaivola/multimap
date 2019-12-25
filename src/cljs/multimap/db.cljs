(ns multimap.db)

(def default-db
  {:map
   {:gps
    {:id nil
     :marker nil
     :position
     {:latitude nil
      :longitude nil
      :accuracy nil
      :heading nil
      :type nil
      :speed nil}}}})
