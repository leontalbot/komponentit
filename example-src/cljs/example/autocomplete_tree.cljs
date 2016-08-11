(ns example.autocomplete-tree
  (:require [komponentit.autocomplete :as autocomplete]
            [reagent.core :as r]
            [devcards.core :as dc :include-macros true]
            [clojure.string :as str]
            [cljs.test :refer-macros [is]]))

(def tree-data
  [{:id 1
    :name "Foo"
    :items [{:id 2
             :name "Bar"
             :items [{:id 3
                      :name "System Module"
                      :price 1380}
                     {:id 4
                      :name "ABC"
                      :price 1340}]}
            {:id 5
             :name "Ooooo"
             :items [{:id 6
                      :name "asdasd"
                      :price 9000}]}]}
   {:id 7
    :name "Bar"
    :items [{:id 8
             :name "A"
             :items [{:id 9
                      :name "Foo"
                      :price 50000}]}]}
   {:id 10
    :name "Lorem ipsum"
    :price 90}])

(dc/defcard-rg tree-autocomplete
  (fn [value _]
    [autocomplete/autocomplete
     {:value @value
      :cb (fn [item] (reset! value (:id item)))
      :search-fields [:name]
      :item->key :id
      :item->text (fn [item]
                    (str (::autocomplete/i item) " " (:name item) " (" (:id item) ")"))
      :value->text (fn [_ x] (str x))
      ;; Enable tree
      :item->items :items
      :items tree-data}])
  (r/atom nil))

(dc/defcard-rg tree-autocomplete-custom-item
  (fn [value _]
    [autocomplete/autocomplete
     {:value @value
      :cb (fn [item] (reset! value (:id item)))
      :search-fields [:name]
      :item->key :id
      :item->text (fn [item]
                    (str (::autocomplete/i item) " " (:name item) " (" (:id item) ")"))
      :value->text (fn [_ x] (str x))
      ;; Enable tree
      :item->items :items
      :items tree-data}])
  (r/atom nil))

(dc/deftest sub-query-match?-test
  (let [match-fn (autocomplete/create-matcher* [:name])]
    (is (= [["foo"] ["bar"]] (autocomplete/sub-query-match? match-fn {:name "foo"} ["foo" "bar"])))))