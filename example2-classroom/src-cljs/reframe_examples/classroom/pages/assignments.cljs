(ns reframe-examples.classroom.pages.assignments
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [taoensso.timbre :as timbre]))

;; -----------------------------------------------------------------------------
;; Events

;; TODO: assignments-related events go here

;; -----------------------------------------------------------------------------
;; Subscriptions

;; TODO: assignments-related subscriptions go here

;; -----------------------------------------------------------------------------
;; Components / Views

(defn AssignmentsPage []
  [:div
    [:h1.title.is-1 "Assignments"]
    [:h2.subtitle.is-4 "TODO: write me :)"]])
