(ns reframe-examples.classroom.pages.attendance
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [taoensso.timbre :as timbre]))

;; -----------------------------------------------------------------------------
;; Events

;; TODO: attendance-related events go here

;; -----------------------------------------------------------------------------
;; Subscriptions

;; TODO: attendance-related subscriptions go here

;; -----------------------------------------------------------------------------
;; Components / Views

(defn AttendancePage []
  [:div
    [:h1.title.is-1 "Attendance"]
    [:h2.subtitle.is-4 "TODO: write me :)"]])
