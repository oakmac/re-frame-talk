(ns reframe-examples.counter.core
  (:require
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [taoensso.timbre :as timbre]))

(def initial-state
  {:num-times-clicked 0})

;; -----------------------------------------------------------------------------
;; Event Handlers: listen for events that were dispatched
;; This is where you make changes to the app-db.

(rf/reg-event-db
  :initialize
  (fn [_db _event]
    initial-state))

(rf/reg-event-db
  :increment
  (fn [db _event]
    (update-in db [:num-times-clicked] inc)))

;; -----------------------------------------------------------------------------
;; Subscriptions: get data out of app-db

(rf/reg-sub
  :click-count
  (fn [db]
    (:num-times-clicked db)))

;; -----------------------------------------------------------------------------
;; Components / Views

(defn BigCount
  []
  (let [current-count @(rf/subscribe [:click-count])]
    [:div
     {:style {:font-size "64px"}}
     current-count]))

(defn Buttons
  []
  [:div.buttons
    [:button.button.is-info.is-large
      {:on-click #(rf/dispatch [:increment])}
      "+1"]])

(defn App
  []
  (let [current-count @(rf/subscribe [:click-count])]
    [:section.section
      [:div.container
       [:h1.title.is-1 "re-frame counter: " current-count]
       ; [BigCount]
       [Buttons]]]))

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
