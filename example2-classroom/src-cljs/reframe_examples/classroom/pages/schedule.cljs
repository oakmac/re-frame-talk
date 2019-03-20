(ns reframe-examples.classroom.pages.schedule
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [taoensso.timbre :as timbre]))

;; -----------------------------------------------------------------------------
;; Events

;; TODO: schedule-related events go here

;; -----------------------------------------------------------------------------
;; Subscriptions

;; TODO: schedule-related subscriptions go here

;; -----------------------------------------------------------------------------
;; Components / Views

(defn SchedulePage []
  [:div
    [:h1.title.is-1 "Schedule"]
    [:h2.subtitle.is-4 "TODO: write me :)"]])
