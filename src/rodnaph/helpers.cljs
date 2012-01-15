
(ns rodnaph.helpers
    (:require [clojure.string :as string]
              [goog.dom :as dom]))

(defn log
    "Write a log message"
    [msg]
    (.log js/console msg))

(defn by-class
    "Fetch elements with specified class"
    [klass]
    (.getElementsByClass goog.dom klass)) 

