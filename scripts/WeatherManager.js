  /**
   * @fileoverview Handles weather condition
   */
  'use strict';
  /**
   * WeatherManager contains set of functions
   * to get id and handle weather condition
   * URL parameter must be from http://api.openweathermap.org
   * since it has the id attribute
   */
  function WeatherManager(urlPath) {
    this.urlPath = urlPath;

    this.RAIN = 'rain';
    this.FOG = 'fog';
    this.CLEAR = 'clear';
    this.CLOUD = 'cloud';

    /**
     * makes an AJAX call to the Weather API
     * @return {Integer} num is the Weather State code
     */
    this.getWeather = function() {
      var num;
      $.ajax({
        url: urlPath,
        type: 'GET',
        async: false,
        success: function(data) {
          num = data.weather[0].id;
        }
      });
      return this.getWeatherType(num);
    };

    /**
     * @param {Integer} id is the weather state Id
     * @return {String} the name of the weather condition
     */
    this.getWeatherType = function(id) {
      if((id >= 200 && id <= 622) || (id >= 900 && id <= 902)) {
      return this.RAIN;
      } else if (id >= 701 && id <= 781) {
        return this.FOG;
      } else if (id === 804) {
        return this.CLOUD;
      } else {
        return this.CLEAR;
      }
    };

    /**
     * sets weather background
     * @param {String} sectionName name of the section whose background to be changed
     * @param {String} weatherState name weather html element name ie: day, night
     */
    this.setWeather = function(sectionName, weatherState) {
      $(sectionName).addClass(weatherState);
    };
  }