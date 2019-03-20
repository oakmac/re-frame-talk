(ns reframe-examples.classroom.pages.students
  (:require
    [clojure.string :as str]
    [oops.core :refer [oget]]
    [re-frame.core :as rf]
    [reagent.core :as reagent]
    [reframe-examples.classroom.data :as data]
    [taoensso.timbre :as timbre]))

;; DEMO TODO:
;; - "clear search" link
;; - show count of filtered students
;; - chain filtered + sorted students

;; -----------------------------------------------------------------------------
;; Events

(rf/reg-event-fx
  :refresh-students
  (fn [cofx _event]
    (let [db (:db cofx)]
      {:db (assoc db :fetching-students? true)
       :fetch-students nil})))

(rf/reg-event-db
  :fetch-students-success
  (fn [db [_ new-students]]
    (assoc db :fetching-students? false
              :students (zipmap (map :id new-students) new-students))))

(rf/reg-event-db
  :update-search-txt
  (fn [db [_ new-txt]]
    (assoc db :search-txt new-txt)))

;; -----------------------------------------------------------------------------
;; Effects
;; NOTE: these functions should have side-effects (ie: not pure functions)

(rf/reg-fx
  :fetch-students
  (fn []
    (timbre/info "Fetching students ...")
    (data/fetch-students
      #(rf/dispatch [:fetch-students-success %])
      #(timbre/error "Failed to fetch students :( TODO: show an error message here"))))

;; -----------------------------------------------------------------------------
;; Subscriptions

(rf/reg-sub
  :fetching-students?
  (fn [db]
    (:fetching-students? db)))

(rf/reg-sub
  :students
  (fn [db]
    (:students db)))

(rf/reg-sub
  :search-txt
  (fn [db]
    (:search-txt db)))

(rf/reg-sub
  :filtered-students
  :<- [:students]
  :<- [:search-txt]
  (fn [[students search-txt]]
    (let [lc-search-txt (str/lower-case search-txt)
          filter-fn (fn [{:keys [name email address]}]
                      (str/includes?
                        (str/lower-case (str name " " email " " address))
                        lc-search-txt))]
      (->> (vals students)
           (filter filter-fn)))))

;; -----------------------------------------------------------------------------
;; Components / Views

(defn RefreshStudentsBtn
  []
  (let [fetching? @(rf/subscribe [:fetching-students?])]
    [:button.button.is-primary
      {:class [(when fetching? "is-loading")]
       :on-click #(rf/dispatch [:refresh-students])}
      "Refresh"]))

(defn SearchInput
  []
  (let [search-txt @(rf/subscribe [:search-txt])]
    [:input.input.is-medium
      {:on-change (fn [js-evt]
                    (rf/dispatch [:update-search-txt (oget js-evt "currentTarget.value")]))
       :placeholder "Search Students …"
       :value search-txt}]))

(defn Toolbar []
  [:div.columns
    [:div.column.is-6 [SearchInput]]
    [:div.column.is-6 [RefreshStudentsBtn]]])

(defn TableHeader []
  [:tr
    [:th "Name"]
    [:th "Email"]
    [:th "Age"]
    [:th "Address"]])

(defn NoStudentsRow []
  [:tr
    [:td {:col-span 4}
      [:div.no-students-msg
        "No students found"]]])
        ;; TODO: "clear search" here

(defn TableRow [idx {:keys [name email age address] :as student}]
  [:tr {:key idx}
    [:td name]
    [:td email]
    [:td age]
    [:td address]])

(defn StudentsTable
  []
  (let [filtered-students @(rf/subscribe [:filtered-students])]
    [:table.table.is-striped.is-fullwidth
      [:thead [TableHeader]]
      [:tbody
        (if (empty? filtered-students)
          [NoStudentsRow]
          (map-indexed TableRow filtered-students))]]))

(defn StudentsPage []
  [:div
    [:h1.title.is-1 "Students"]
    [Toolbar]
    [StudentsTable]])
