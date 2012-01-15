
(ns rodnaph.helpers
    (:require [goog.dom :as dom]))

(defn- log
    "Write a log message"
    [msg]
    (.log js/console msg))

(defn by-class
    "Fetch elements with specified class"
    [klass]
    (.getElementsByClass goog.dom klass)) 

(extend-type js/NodeList 
    ISeqable 
    (-seq [array] (array-seq array 0))) 

