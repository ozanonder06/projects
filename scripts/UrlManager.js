/** 
 * @fileoverview handles the string query, read values from the key paramaters.
 */
'use strict';

function UrlManager() {

  /* sets the title of itemId, to the element
   * by getting its data-value and setting text
   */
  this.getQueryString = function() { 
    var stringQueries = document.URL.split('?')[1];
    if(stringQueries==null) {
      return 0;
    } else {
      return stringQueries; 
    }
  };

  /**
   * @param {String} key is the name of the key in string query
   * @return {String} param[1] is the value that corresponds to key
   */
  this.getValue = function(key) {
    var qs = this.getQueryString();
    if(qs!=0) {
      var qList = new Array();
      qList = qs.split('&');

      for(var i=0; i<qList.length; i++) {
        var param = qList[i].split('=');
        if(param[0]==key) {
          return param[1];
        }
      }
    }
  };
}
