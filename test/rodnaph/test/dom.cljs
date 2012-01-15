
(ns rodnaph.test.dom
    (:require [rodnaph.dom :as dom]
              [goog.dom :as gdom]))

(def node (gdom/createDom "div" "foo bazzle"))

(defn test-all
    []
    ; has-class?
    (assert (dom/has-class? node "foo"))
    (assert (not (dom/has-class? node "bar")))
    ; has-classes?
    (assert (dom/has-classes? node "foo" "bazzle"))
    (assert (not (dom/has-classes? node "foo" "bar")))
    ; add-class
    (dom/add-class node "bar")
    (assert (dom/has-class? node "bar"))
    ; remove-class
    (dom/remove-class node "foo")
    (assert (not (dom/has-class? node "foo")))
    ; toggle-class
    (assert (dom/has-class? node "bar"))
    (dom/toggle-class node "bar")
    (assert (not (dom/has-class? node "bar")))
    (dom/toggle-class node "bar")
    (assert (dom/has-class? node "bar"))
)

