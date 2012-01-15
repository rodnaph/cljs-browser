
(ns rodnaph
    (:require [rodnaph.test.dom :as dom]))

(defn ^:export browser-tests
    "Run tests in the browser"
    []
    (dom/test-all))

