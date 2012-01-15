
(ns rodnaph.dnd
    (:require [goog.dom :as dom]
              [goog.events :as events]
              [goog.fx.DragDropGroup :as DragDropGroup]))

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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn drag-group
    "Created a drag group with the items matching klass"
    [klass]
    (let [dnd (goog.fx.DragDropGroup.)]
        (doseq [target (by-class klass)]
            (.addItem dnd target))
        dnd))

(defn drag-over-anchor
    "Handler for when an anchor is dragged over"
    [evt]
    (log "OVER"))

(defn drag-out-anchor
    "Handler for when an dragging off an anchor"
    [evt]
    (log "OUT"))

(defn add-anchor-events
    "Add events for target anchor elements"
    [anchors]
    (.listen goog.events anchors "dragover" drag-over-anchor)
    (.listen goog.events anchors "dragout" drag-out-anchor))

(defn ^:export init
    "Initialise draggable elements"
    []
    (let [sources (drag-group "source")
          anchors (drag-group "target-anchor")]
        (.addTarget sources anchors)
        (.init sources)
        (.init anchors)
        (add-anchor-events anchors)
    ))

