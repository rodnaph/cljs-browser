
(ns rodnaph.dnd
    (:require [rodnaph.helpers :as helpers]
              [rodnaph.dom :as dom]
              [goog.events :as events]
              [goog.fx.DragDropGroup :as DragDropGroup]))

(defn drag-group
    "Created a drag group with the items matching klass"
    [klass]
    (let [dnd (goog.fx.DragDropGroup.)]
        (doseq [target (helpers/by-class klass)]
            (.addItem dnd target))
        dnd))

(defn drag-anchor
    "Handler for when an dragging off an anchor"
    [evt]
    (dom/toggle-class evt.dropTargetItem.element "hovered"))

(defn add-anchor-events
    "Add events for target anchor elements"
    [anchors]
    (.listen goog.events anchors "dragover" drag-anchor)
    (.listen goog.events anchors "dragout" drag-anchor))

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

