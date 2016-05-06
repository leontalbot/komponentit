(ns komponentit.mixins
  (:require [komponentit.util :as u]
            [goog.events :as events])
  (:import [goog.events EventType]))

;;
;; Mixin: Close the component if user clicks outside the element
;;

(defn create-closable
  "Returns a function which can be called to remove the event handlers."
  [close-cb]
  (let [click-handler
        (events/listen js/window EventType.CLICK (fn [e]
                                                   (close-cb e)
                                                   nil))
        key-handler
        (events/listen js/window EventType.KEYUP (fn [e]
                                                   (case (.-key e)
                                                     "Esc" (close-cb e)
                                                     nil)
                                                   nil))]
    (fn []
      (events/unlistenByKey click-handler)
      (events/unlistenByKey key-handler))))
