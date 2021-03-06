   
(ns rodnaph.dom
    (:require [clojure.string :as string]
              [goog.dom :as dom]))

(extend-type js/NodeList 
    ISeqable 
    (-seq [array] (array-seq array 0))) 

(defn- classes
    "Returns an elements classes"
    [el]
    (string/split (.getAttribute el "class") #"\s+"))

(defn- affect-class
    "Affect an elements classes with the specified mutator"
    [el klass mutator]
    (->> (classes el)
         (filter #(not= klass %))
         (mutator klass)
         (string/join " ")
         (.setAttribute el "class")))

(defn has-class?
    "Indicates if an element has a class"
    [el klass]
    (some #(= klass %) (classes el)))

(defn has-classes?
    "Indicates if the item has all the specified classes"
    [el & classes]
    (every? (partial has-class? el) classes))

(defn toggle-class
    "Toggles a class on an element"
    [el klass]
    (if (has-class? el klass)
        (remove-class el klass)
        (add-class el klass)))

(defn add-class
    "Adds a class to an element"
    [el klass]
    (affect-class el klass cons))

(defn remove-class
    "Removed a class from an element"
    [el klass]
    (affect-class el klass #(identity %2)))

