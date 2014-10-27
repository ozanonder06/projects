  /**
   * @fileoverview Handles day and time backgrounds
   */
  'use strict';

  function TimeManager() {
    this.DAY = 'day';
    this.NIGHT = 'night';
    this.TIME_OFFSET = 7; /*offset between UTC and Pasific Time Zone*/

    /**
     * calculates the local time with a given time offset
     * finds time since 1970 for the local time zone
     * finds the number of ms offset
     */
    this.getTimeZone = function(offset) {
      var localTime, localOffset, utc, timeZone;
      var d = new Date();
      localTime = d.getTime();

      /* time difference between UTC time and local time in ms*/
      localOffset = d.getTimezoneOffset() * 60000;

      /**
       * UTC = ms(localTime) + ms(offset) where ms is milliseconds
       **/
      utc = localTime + localOffset;
      timeZone = utc - (3600000 * offset);
      return new Date(timeZone);
    };

    this.isDay = function() {           
      var date = this.getTimeZone(this.TIME_OFFSET);
      var hour = date.getHours();
      return((hour >= 6)&&(hour <= 18));
    };

    /**
     * sets the dayTimeElement to the sectionName
     * @param {String} sectionName is the selector of the html element
     * @param {String} dayTimeElement is name of day/night backgrounds
     */
    this.setDayTime = function(sectionName, dayTimeElement) {
      $(sectionName).addClass(dayTimeElement);
    };

    this.getCurrentTime = function(sectionName) {
      return this.isDay() ? this.DAY : this.NIGHT;
    }
  }