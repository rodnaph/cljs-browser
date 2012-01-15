
goog.provide( 'boxuk.artform' );
goog.provide( 'boxuk.artform.CarcassEditor' );

goog.require('goog.events');
goog.require('goog.dom');
goog.require('goog.fx.DragDrop');
goog.require('goog.fx.DragDropItem');
goog.require('goog.fx.DragDropGroup');
goog.require('goog.fx.DragListGroup');

/**
 * Carcass Editor
 *
 * Allows editing a carcass.
 */
boxuk.artform.CarcassEditor = function() {};

boxuk.artform.CarcassEditor.prototype = {

    init: function() {

        var shelves = new goog.fx.DragDropGroup();
        var shelfElements = goog.dom.getElementsByClass( 'shelf' );
        var shelfAnchors = new goog.fx.DragDropGroup();
        var shelfAnchorElements = goog.dom.getElementsByClass( 'shelf-anchor' );

        for ( var i=0; i<shelfElements.length; i++ ) {
            shelves.addItem( shelfElements[i] );
        }

        for ( var i=0; i<shelfAnchorElements.length; i++ ) {
            shelfAnchors.addItem( shelfAnchorElements[i] );
        }

        shelves.addTarget( shelfAnchors );
        shelves.init();
        shelfAnchors.init();

        var dragOverAnchor = function( event ) {
            event.dropTargetItem.element.className = 'shelf-anchor hovered';
        };

        var dragOutAnchor = function( event ) {
            event.dropTargetItem.element.className = 'shelf-anchor';
        };

        var dropShelf = function( event ) {
            event.dropTargetItem.element.className = 'shelf-anchor has-shelf';
        };

        goog.events.listen( shelfAnchors, 'dragover', dragOverAnchor );
        goog.events.listen( shelfAnchors, 'dragout', dragOutAnchor );
        goog.events.listen( shelfAnchors, 'drop', dropShelf );

    }

};

boxuk.artform.initCarcassEditor = function() {
    var editor = new boxuk.artform.CarcassEditor();
    editor.init();
};

