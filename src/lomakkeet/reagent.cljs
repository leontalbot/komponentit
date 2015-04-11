(ns lomakkeet.reagent
  (:refer-clojure :exclude [update])
  (:require [reagent.ratom :as ratom]
            [lomakkeet.core :as core]
            [lomakkeet.util :refer [dissoc-in]]
            [lomakkeet.reagent.impl :as impl]
            [lomakkeet.reagent.datepicker :as date]
            [lomakkeet.reagent.filepicker :as file]))

;; BUILD

(defn form-group-com [form]
  (:form-group form impl/default-form-group))

(defn input [form label ks & [opts]]
  [(form-group-com form) form impl/input* (assoc (merge (:opts form) opts) :label label :ks ks)])

(defn textarea [form label ks & [opts]]
  [(form-group-com form) form impl/input* (assoc (merge (:opts form) opts) :el impl/input-textarea :label label :ks ks)])

(defn static [form label ks & [opts]]
  [(form-group-com form) form impl/input* (assoc (merge (:opts form) opts) :el impl/input-static :label label :ks ks)])

(defn checkbox [form label ks & [opts]]
  [(form-group-com form) form impl/checkbox* (assoc (merge (:opts form) opts) :label label :ks ks)])

(defn select [form label ks options & [opts]]
  [(form-group-com form) form impl/select* (assoc (merge (:opts form) opts) :label label :ks ks :options options)])

(defn date [form label ks & [opts]]
  [(form-group-com form) form date/date* (assoc (merge (:opts form) opts) :label label :ks ks)])

(defn file [form label ks & [opts]]
  [(form-group-com form) form file/file* (assoc (merge (:opts form) opts) :label label :ks ks)])

(def ->fs core/->fs)
(def reset core/reset)
(def commit core/commit)
(def save core/save)
(def update core/update)
(def dirty? core/dirty?)
(def errors? core/errors?)
