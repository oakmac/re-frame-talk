(ns reframe-examples.classroom.components.tabs
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [taoensso.timbre :as timbre]))

;; -----------------------------------------------------------------------------
;; Events

(rf/reg-event-db
  :update-tab
  (fn [db [_ new-tab]]
    (assoc db :active-tab new-tab)))

;; -----------------------------------------------------------------------------
;; Subscriptions

(rf/reg-sub
  :active-tab
  (fn [db]
    (:active-tab db)))

;; -----------------------------------------------------------------------------
;; Components / Views

(defn Tab [{:keys [active? label kwd]}]
  [:li {:class (when active? "is-active")
        :on-click #(rf/dispatch [:update-tab kwd])}
    [:a label]])

(defn Tabs []
  (let [active-tab @(rf/subscribe [:active-tab])]
    [:div.tabs.is-boxed.is-medium
      [:ul
        [Tab {:active? (= active-tab :students)
              :label "Students"
              :kwd :students}]
        [Tab {:active? (= active-tab :assignments)
              :label "Assignments"
              :kwd :assignments}]
        [Tab {:active? (= active-tab :attendance)
              :label "Attendance"
              :kwd :attendance}]
        [Tab {:active? (= active-tab :schedule)
              :label "Schedule"
              :kwd :schedule}]]]))
