'use strict';
/**
 * @fileoverview 
 * reads the meta data from PortfolioItems.js
 * PortfoliItems is the name of the array that contains portfolio
 * objects. It reads the picture1 value of each object
 * and assigns as background depending on the specified type.
 */

function PortfolioGalleryManager() {

  /**
   * sets the given css classes'
   * backgorund-pictures with picture1 attribute
   * of ITEMS array with given type.
   * @param {String} section is the css class name of an element
   * @param {String} itemType is the id name of desired menu link
   */
  this.populateWithType = function(section, itemType){
    var index = 0; /*index of ITEMS*/
    var n = PortfolioItems.length;
    var found = false;

    $(section).each(function() {
      var itemID = '#'+this.id;

      while(!found) {
        for(var i = 0; i < PortfolioItems[index].type.length; i++) {
          if(itemType === PortfolioItems[index].type[i]) {
            found = true;
            $(itemID).css('background-image',PortfolioItems[index].picture1);
            break;
          }
        }
        if(index === n-1) {
          index = 0;
        } else {
          index++;
        }
      }
      found = false;
    });  
  };

  /**
   * sets the given css classes'
   * backgorund-pictures with picture1 attribute
   * of ITEMS array.
   * @param {String} section is the css class name of an element
   */
  this.populateAll = function(section){
    var itemIndex = 0; 
    var n = PortfolioItems.length;
    $(section).each(function() {
      var itemID = '#'+this.id;
      $(itemID).css('background-image',PortfolioItems[itemIndex].picture1);
      if(itemIndex === n-1) {
        itemIndex = 0;
      } else {
        itemIndex++;
      }           
    });  
  };   
}
