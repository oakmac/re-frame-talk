(ns reframe-examples.classroom.core
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [reframe-examples.classroom.components.tabs :as tabs]
    [reframe-examples.classroom.pages.assignments :as assignments-page]
    [reframe-examples.classroom.pages.attendance :as attendance-page]
    [reframe-examples.classroom.pages.schedule :as schedule-page]
    [reframe-examples.classroom.pages.students :as students-page]
    [taoensso.timbre :as timbre]))

(def initial-state
  {:fetching-students? true
   :search-txt ""
   :students nil
   :active-tab :students})

;; -----------------------------------------------------------------------------
;; Events

(rf/reg-event-fx
  :initialize
  (fn [_cofx _event]
    {:db initial-state
     :fetch-students nil}))

;; -----------------------------------------------------------------------------
;; Components / Views

(defn App
  []
  (let [tab @(rf/subscribe [:active-tab])]
    [:section.section
      [:div.container
        [tabs/Tabs]
        (case tab
          :students [students-page/StudentsPage]
          :assignments [assignments-page/AssignmentsPage]
          :attendance [attendance-page/AttendancePage]
          :schedule [schedule-page/SchedulePage]
          (timbre/warn "No page-level component found for: " tab))]]))

;; -----------------------------------------------------------------------------
;; Init + Render

(defn- by-id [id]
  (js/document.getElementById id))

;; NOTE: this function is called by shadow-cljs on every hot module reload
(defn render! []
  (reagent/render [App] (by-id "app")))

(defn init! []
  (rf/dispatch-sync [:initialize])
  ;; start rendering
  (render!))
