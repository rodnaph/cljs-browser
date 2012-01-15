
(ns rodnaph
    (:require [rodnaph.test.dom :as dom]
              [rodnaph.test.helpers :as helpers]))

(defn ^:export browser-tests
    "Run tests in the browser"
    []
    (dom/test-all)
    (helpers/test-all))

