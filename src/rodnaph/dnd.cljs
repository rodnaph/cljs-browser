
(ns rodnaph.dnd
    (:require [rodnaph.helpers :as helpers]
              [rodnaph.dom :as dom]
              [goog.dom :as gdom]
              [goog.events :as events]
              [goog.fx.DragDropGroup :as DragDropGroup]))

(defn drag-group
    "Created a drag group with the items matching klass"
    [klass]
    (let [dnd (goog.fx.DragDropGroup.)]
        (doseq [target (helpers/by-class klass)]
            (.addItem dnd target))
        dnd))

(defn add-dropped-item
    "Adds a dropped to the element"
    [el]
    (gdom/appendChild el 
        (gdom/createDom "div" "dropped-item")))

(defn can-drop-item?
    "Indicates of the item can be dropped on the target"
    [source target]
    (and (dom/has-class? source "droppable-item")
         (dom/has-class? target "droppable-target")))

(defn handle-drop
    "Handler for when a drop occurs over an anchor"
    [evt]
    (let [target evt.dropTargetItem.element
          source evt.dragSourceItem.element]
        (dom/remove-class target "hovered")
        (if (can-drop-item? source target)
            (add-dropped-item target)
            (if (dom/has-class? source "makes-droppable")
                (dom/add-class target "droppable-target")))))

(defn toggle-hover
    "Toggle the hover class on the drag target"
    [evt]
    (dom/toggle-class evt.dropTargetItem.element "hovered"))

(defn add-drag-events
    "Add events for target anchor elements"
    [anchors]
    (.listen goog.events anchors "drop" handle-drop)
    (.listen goog.events anchors "dragover" toggle-hover)
    (.listen goog.events anchors "dragout" toggle-hover))

(defn ^:export init
    "Initialise draggable elements"
    []
    (let [sources (drag-group "source")
          anchors (drag-group "target-anchor")
          items (drag-group "draggable-item")
          make-drag-source #(.addTarget % anchors)
          init #(. % (init))]
        (dorun (map make-drag-source [sources items]))
        (dorun (map init [sources anchors items]))
        (add-drag-events anchors)
    ))

