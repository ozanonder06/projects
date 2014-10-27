'use strict';

var WEATHER_MAP = {
  rain: {
    cssClass: 'rain'
  },
  cloud: {
    cssClass: 'cloud'
  },
  clear: {
    cssClass: ''
  },
  fog: {
    cssClass: 'fog'
  }
}

$(document).ready(function(){
  window.resizeTo(500,500);
  loadDefault();
  var w = new WeatherManager('http://api.openweathermap.org/data/2.5/weather?lat=37.78&lon=-122.4');
  var portfolioHandler = new PortfolioManager();
  var gallery = new PortfolioGalleryManager();
  var time = new TimeManager();
  var urlManager = new UrlManager();
  gallery.populateAll('.item');

  /**
   *css selector names of the elements
   */
  var section1 = '#section1';
  var weatherHolder = '.weather';

  /**
   *this functionality is added for the QA purpose
   *@example <url>?t=day&f=rain
   *where t represents time [day, night]
   *where f represents forecast [sun, fog, rain]
   */
  var timeFlag = urlManager.getValue('t') || time.getCurrentTime();
  time.setDayTime(section1, timeFlag);

  var weatherFlag = urlManager.getValue('f') || w.getWeather();
  w.setWeather(weatherHolder, WEATHER_MAP[weatherFlag].cssClass);

  /**
   *portfolio item handler
   */
  $('.item').click(function () {
    portfolioHandler.setTitle(this, '.project-detail-title');
    portfolioHandler.setTitle(this, '.panel-title');
    portfolioHandler.setPortfolioPicture(this, '.project-detail-image');
    portfolioHandler.slideSection('.portfolio-main', 'left', 500);
    portfolioHandler.slideSection('.project-detail', 'right', 500);
  });

  $('.portfolio-menu-link').click(function () {
    if(!isActive(this)) {
      var menuLink = this;
      portfolioHandler.slideSection('.portfolio-gallery', 'left', 450).done(function(){
        var itemType = $(menuLink).attr('id');
        if(itemType ==='all') {
          gallery.populateAll('.item');
        }  else {
           gallery.populateWithType('.item', itemType);
        }
      });
      portfolioHandler.slideSection('.portfolio-gallery','right', 450);
      $('.portfolio-menu-link').removeClass('active');
      deactivateItem('.portfolio-menu-link');
      activateItem(this);
    }
  });

  /**
   *main menu handler
   */
  $('.menu-open,#menu-close').click(function () {

    if ($('.menu-open').is(':visible')) {
      $('.menu-open').fadeOut(function () {
        $('.slide-menu').toggle('slide', {
          direction: 'right'
        }, 400);
      });
    } else {
        $('.slide-menu').toggle('slide', {
          direction: 'right'
        }, 400, function(){ $('.menu-open').fadeIn();
        });
      }
  });

  $('#fullpage').fullpage({
  anchors: ['home', 'work', 'blog', 'team', 'services', 'contact', 'career'],
  menu: '#header' ,
  resize: false,

  /**
   * responsive screen depending on the screen resizing
   * 1)hides footer button on max-width 480px
   * 2)disables full screen scrolling on max-width 480px
   */
  afterResize: function(){
    
    if ($(window).width() <= 480){
      $('.footer-button').hide();
      $.fn.fullpage.setAutoScrolling(false);
    } else {
      $('.footer-button').show();
      $.fn.fullpage.setAutoScrolling(true); 
    }   
  },

  //menu title that responds to the scrolled section
  afterLoad: function(anchorLink, index){
     switch(index){
       case 1:
            changeMenuTitle('Home');
            setMenu('Home');
            break;

       case 2:
            changeMenuTitle('Our Work');
            setMenu('Our Work');
            break;

       case 3:
            changeMenuTitle('Grio Blog');
            setMenu('Grio Blog');
            break;

       case 4:
            changeMenuTitle('Team Grio');
            setMenu('Team Grio');
            break;

       case 5:
            changeMenuTitle('Services');
            setMenu('Services');
            break;
     }
  },

  onLeave: function(index, nextIndex, direction){
    switch(nextIndex){
       case 1:
            activateItem('#homeItem');
            break;
       case 2:
            activateItem('#ourWorkItem');
            break;
       case 3:
            activateItem('#grioBlogItem');
            break;
       case 4:
            activateItem('#teamGrioItem');
            break;
       case 5:
            activateItem('#servicesItem');
            break;
     }
     switch(index){
       case 1:
            deactivateItem('#homeItem');
            break;
       case 2:
            deactivateItem('#ourWorkItem');
            break;
       case 3:
            deactivateItem('#grioBlogItem');
            break;
       case 4:
            deactivateItem('#teamGrioItem');
            break;
       case 5:
            deactivateItem('#servicesItem');
            break;
     }
  }
  });
});

//changes the menu button title which
function changeMenuTitle(titleName) {
   $('#button-title').html(titleName);
}

//sets the section name in the menu
function setMenu(sectionName) {
   $('#menuID').html(sectionName);
}

function activateItem(itemID) {
  $(itemID).addClass('active');
}

function deactivateItem(itemID) {
  $(itemID).removeClass('active');
}

function isActive(itemID) {
  return $(itemID).hasClass('active');
}

//load initial styles
function loadDefault() {
  setMenu('Home');
  changeMenuTitle('Home');
  activateItem('#homeItem');
}