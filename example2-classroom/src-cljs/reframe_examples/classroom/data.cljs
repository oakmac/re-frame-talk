(ns reframe-examples.classroom.data
  (:require
    [ajax.core :refer [GET]]
    [taoensso.timbre :as timbre]))

(defn fetch-students [success-fn error-fn]
  (js/setTimeout
    (fn []
      (GET "students.json"
        {:keywords? true
         :response-format :json
         :handler success-fn
         :error-handler error-fn}))
    (+ 250 (rand-int 400))))
