/**
 * @fileoverview handles the project detail section.
 * loads components including titles and picture
 */
'use strict';

function PortfolioManager()
{
  /**
   * sets the title of itemId to the element
   * by getting its data-value element
   */
  this.setTitle = function(itemId, element) { 
    var name = $(itemId).data('value')
    $(element).text(name);
  };
  
  /**
   * slides the a given protfolio section
   * @param {String} panel, html class/id name of the sliding panel 
   * @param {String} direction, slide direction of the panel
   * @param {String} milliseconds time(ms) takes to complete slide
   * @param {function}
   */
  this.slideSection = function(panel, direction, milliSeconds, callBack) { 
    var deferred = $.Deferred();
    $(panel).toggle('slide', { direction: direction }, milliSeconds, function() {
      deferred.resolve();
    });
    return deferred.promise(); 
  };

  /**
   * sets the background image of itemId to element
   * @param {String} itemId, html class/id name of the item
   * @param {String} element, class/id name of the element
   * @param {String} milliseconds time(ms) takes to complete slide
   */
   this.setPortfolioPicture = function(itemId, element) {
    var path = $(itemId).css('backgroundImage');
    $(element).css('background-image',path);
   };
}
