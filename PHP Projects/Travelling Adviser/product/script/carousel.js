//// JQUERY PLUGIN - EASING
jQuery.easing={easein:function(x,t,b,c,d){return c*(t/=d)*t+b},easeinout:function(x,t,b,c,d){if(t<d/2)return 2*c*t*t/(d*d)+b;var a=t-d/2;return-2*c*a*a/(d*d)+2*c*a/d+c/2+b},easeout:function(x,t,b,c,d){return-c*t*t/(d*d)+2*c*t/d+b},expoin:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(Math.exp(Math.log(c)/d*t))+b},expoout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}return a*(-Math.exp(-Math.log(c)/d*(t-d))+c+1)+b},expoinout:function(x,t,b,c,d){var a=1;if(c<0){a*=-1;c*=-1}if(t<d/2)return a*(Math.exp(Math.log(c/2)/(d/2)*t))+b;return a*(-Math.exp(-2*Math.log(c/2)/d*(t-d))+c+1)+b},bouncein:function(x,t,b,c,d){return c-jQuery.easing['bounceout'](x,d-t,0,c,d)+b},bounceout:function(x,t,b,c,d){if((t/=d)<(1/2.75)){return c*(7.5625*t*t)+b}else if(t<(2/2.75)){return c*(7.5625*(t-=(1.5/2.75))*t+.75)+b}else if(t<(2.5/2.75)){return c*(7.5625*(t-=(2.25/2.75))*t+.9375)+b}else{return c*(7.5625*(t-=(2.625/2.75))*t+.984375)+b}},bounceinout:function(x,t,b,c,d){if(t<d/2)return jQuery.easing['bouncein'](x,t*2,0,c,d)*.5+b;return jQuery.easing['bounceout'](x,t*2-d,0,c,d)*.5+c*.5+b},elasin:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return-(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b},elasout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d)==1)return b+c;if(!p)p=d*.3;if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);return a*Math.pow(2,-10*t)*Math.sin((t*d-s)*(2*Math.PI)/p)+c+b},elasinout:function(x,t,b,c,d){var s=1.70158;var p=0;var a=c;if(t==0)return b;if((t/=d/2)==2)return b+c;if(!p)p=d*(.3*1.5);if(a<Math.abs(c)){a=c;var s=p/4}else var s=p/(2*Math.PI)*Math.asin(c/a);if(t<1)return-.5*(a*Math.pow(2,10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p))+b;return a*Math.pow(2,-10*(t-=1))*Math.sin((t*d-s)*(2*Math.PI)/p)*.5+c+b},backin:function(x,t,b,c,d){var s=1.70158;return c*(t/=d)*t*((s+1)*t-s)+b},backout:function(x,t,b,c,d){var s=1.70158;return c*((t=t/d-1)*t*((s+1)*t+s)+1)+b},backinout:function(x,t,b,c,d){var s=1.70158;if((t/=d/2)<1)return c/2*(t*t*(((s*=(1.525))+1)*t-s))+b;return c/2*((t-=2)*t*(((s*=(1.525))+1)*t+s)+2)+b},linear:function(x,t,b,c,d){return c*t/d+b}};

//// JQUERY PLUGIN - TABS
(function($){$.extend({tabs:{remoteCount:0}});$.fn.tabs=function(initial,settings){if(typeof initial=='object')settings=initial;settings=$.extend({initial:(initial&&typeof initial=='number'&&initial>0)?--initial:0,disabled:null,bookmarkable:$.ajaxHistory?true:false,remote:false,spinner:'Loading&#8230;',hashPrefix:'remote-tab-',fxFade:null,fxSlide:null,fxShow:null,fxHide:null,fxSpeed:'normal',fxShowSpeed:null,fxHideSpeed:null,fxAutoHeight:false,onClick:null,onHide:null,onShow:null,navClass:'tabs-nav',selectedClass:'tabs-selected',disabledClass:'tabs-disabled',containerClass:'tabs-container',hideClass:'tabs-hide',loadingClass:'tabs-loading',tabStruct:'div'},settings||{});$.browser.msie6=$.browser.msie6||$.browser.msie&&typeof XMLHttpRequest=='function';function unFocus(){scrollTo(0,0);}return this.each(function(){var container=this;var nav=$('ul.'+settings.navClass,container);nav=nav.size()&&nav||$('>ul:eq(0)',container);var tabs=$('a',nav);if(settings.remote){tabs.each(function(){var id=settings.hashPrefix+(++$.tabs.remoteCount),hash='#'+id,url=this.href;this.href=hash;$('<div id="'+id+'" class="'+settings.containerClass+'"></div>').appendTo(container);$(this).bind('loadRemoteTab',function(e,callback){var $$=$(this).addClass(settings.loadingClass),span=$('span',this)[0],tabTitle=span.innerHTML;if(settings.spinner){span.innerHTML='<em>'+settings.spinner+'</em>';}setTimeout(function(){$(hash).load(url,function(){if(settings.spinner){span.innerHTML=tabTitle;}$$.removeClass(settings.loadingClass);callback&&callback();});},0);});});}var containers=$('div.'+settings.containerClass,container);containers=containers.size()&&containers||$('>'+settings.tabStruct,container);nav.is('.'+settings.navClass)||nav.addClass(settings.navClass);containers.each(function(){var $$=$(this);$$.is('.'+settings.containerClass)||$$.addClass(settings.containerClass);});var hasSelectedClass=$('li',nav).index($('li.'+settings.selectedClass,nav)[0]);if(hasSelectedClass>=0){settings.initial=hasSelectedClass;}if(location.hash){tabs.each(function(i){if(this.hash==location.hash){settings.initial=i;if(($.browser.msie||$.browser.opera)&&!settings.remote){var toShow=$(location.hash);var toShowId=toShow.attr('id');toShow.attr('id','');setTimeout(function(){toShow.attr('id',toShowId);},500);}unFocus();return false;}});}if($.browser.msie){unFocus();}containers.filter(':eq('+settings.initial+')').show().end().not(':eq('+settings.initial+')').addClass(settings.hideClass);$('li',nav).removeClass(settings.selectedClass).eq(settings.initial).addClass(settings.selectedClass);tabs.eq(settings.initial).trigger('loadRemoteTab').end();if(settings.fxAutoHeight){var _setAutoHeight=function(reset){var heights=$.map(containers.get(),function(el){var h,jq=$(el);if(reset){if($.browser.msie6){el.style.removeExpression('behaviour');el.style.height='';el.minHeight=null;}h=jq.css({'min-height':''}).height();}else{h=jq.height();}return h;}).sort(function(a,b){return b-a;});if($.browser.msie6){containers.each(function(){this.minHeight=heights[0]+'px';this.style.setExpression('behaviour','this.style.height = this.minHeight ? this.minHeight : "1px"');});}else{containers.css({'min-height':heights[0]+'px'});}};_setAutoHeight();var cachedWidth=container.offsetWidth;var cachedHeight=container.offsetHeight;var watchFontSize=$('#tabs-watch-font-size').get(0)||$('<span id="tabs-watch-font-size">M</span>').css({display:'block',position:'absolute',visibility:'hidden'}).appendTo(document.body).get(0);var cachedFontSize=watchFontSize.offsetHeight;setInterval(function(){var currentWidth=container.offsetWidth;var currentHeight=container.offsetHeight;var currentFontSize=watchFontSize.offsetHeight;if(currentHeight>cachedHeight||currentWidth!=cachedWidth||currentFontSize!=cachedFontSize){_setAutoHeight((currentWidth>cachedWidth||currentFontSize<cachedFontSize));cachedWidth=currentWidth;cachedHeight=currentHeight;cachedFontSize=currentFontSize;}},50);}var showAnim={},hideAnim={},showSpeed=settings.fxShowSpeed||settings.fxSpeed,hideSpeed=settings.fxHideSpeed||settings.fxSpeed;if(settings.fxSlide||settings.fxFade){if(settings.fxSlide){showAnim['height']='show';hideAnim['height']='hide';}if(settings.fxFade){showAnim['opacity']='show';hideAnim['opacity']='hide';}}else{if(settings.fxShow){showAnim=settings.fxShow;}else{showAnim['min-width']=0;showSpeed=settings.bookmarkable?50:1;}if(settings.fxHide){hideAnim=settings.fxHide;}else{hideAnim['min-width']=0;hideSpeed=settings.bookmarkable?50:1;}}var onClick=settings.onClick,onHide=settings.onHide,onShow=settings.onShow;tabs.bind('triggerTab',function(){var li=$(this).parents('li:eq(0)');if(container.locked||li.is('.'+settings.selectedClass)||li.is('.'+settings.disabledClass)){return false;}var hash=this.hash;if($.browser.msie){$(this).trigger('click');if(settings.bookmarkable){$.ajaxHistory.update(hash);location.hash=hash.replace('#','');}}else if($.browser.safari){var tempForm=$('<form action="'+hash+'"><div><input type="submit" value="h" /></div></form>').get(0);tempForm.submit();$(this).trigger('click');if(settings.bookmarkable){$.ajaxHistory.update(hash);}}else{if(settings.bookmarkable){location.hash=hash.replace('#','');}else{$(this).trigger('click');}}});tabs.bind('disableTab',function(){var li=$(this).parents('li:eq(0)');if($.browser.safari){li.animate({opacity:0},1,function(){li.css({opacity:''});});}li.addClass(settings.disabledClass);});if(settings.disabled&&settings.disabled.length){for(var i=0,k=settings.disabled.length;i<k;i++){tabs.eq(--settings.disabled[i]).trigger('disableTab').end();}};tabs.bind('enableTab',function(){var li=$(this).parents('li:eq(0)');li.removeClass(settings.disabledClass);if($.browser.safari){li.animate({opacity:1},1,function(){li.css({opacity:''});});}});tabs.bind('click',function(e){var trueClick=e.clientX;var clicked=this,li=$(this).parents('li:eq(0)'),toShow=$(this.hash),toHide=containers.filter(':visible');if(container['locked']||li.is('.'+settings.selectedClass)||li.is('.'+settings.disabledClass)||typeof onClick=='function'&&onClick(this,toShow[0],toHide[0])===false){this.blur();return false;}container['locked']=true;if(toShow.size()){if($.browser.msie&&settings.bookmarkable){var toShowId=this.hash.replace('#','');toShow.attr('id','');setTimeout(function(){toShow.attr('id',toShowId);},0);}function switchTab(){if(settings.bookmarkable&&trueClick){$.ajaxHistory.update(clicked.hash);}toHide.animate(hideAnim,hideSpeed,function(){$(clicked).parents('li:eq(0)').addClass(settings.selectedClass).siblings().removeClass(settings.selectedClass);if(typeof onHide=='function'){onHide(clicked,toShow[0],toHide[0]);}var resetCSS={display:'',overflow:'',height:''};if(!$.browser.msie){resetCSS['opacity']='';}toHide.addClass(settings.hideClass).css(resetCSS);toShow.removeClass(settings.hideClass).animate(showAnim,showSpeed,function(){toShow.css(resetCSS);if($.browser.msie){toHide[0].style.filter='';toShow[0].style.filter='';}if(typeof onShow=='function'){onShow(clicked,toShow[0],toHide[0]);}container['locked']=null;});});}if(!settings.remote){switchTab();}else{$(clicked).trigger('loadRemoteTab',[switchTab]);}}else{alert('There is no such container.');}var scrollX=window.pageXOffset||document.documentElement&&document.documentElement.scrollLeft||document.body.scrollLeft||0;var scrollY=window.pageYOffset||document.documentElement&&document.documentElement.scrollTop||document.body.scrollTop||0;setTimeout(function(){window.scrollTo(scrollX,scrollY);},0);this.blur();return settings.bookmarkable&&!!trueClick;});if(settings.bookmarkable){$.ajaxHistory.initialize(function(){tabs.eq(settings.initial).trigger('click').end();});}});};var tabEvents=['triggerTab','disableTab','enableTab'];for(var i=0;i<tabEvents.length;i++){$.fn[tabEvents[i]]=(function(tabEvent){return function(tab){return this.each(function(){var nav=$('ul.tabs-nav',this);nav=nav.size()&&nav||$('>ul:eq(0)',this);var a;if(!tab||typeof tab=='number'){a=$('li a',nav).eq((tab&&tab>0&&tab-1||0));}else if(typeof tab=='string'){a=$('li a[@href$="#'+tab+'"]',nav);}a.trigger(tabEvent);});};})(tabEvents[i]);}$.fn.activeTab=function(){var selectedTabs=[];this.each(function(){var nav=$('ul.tabs-nav',this);nav=nav.size()&&nav||$('>ul:eq(0)',this);var lis=$('li',nav);selectedTabs.push(lis.index(lis.filter('.tabs-selected')[0])+1);});return selectedTabs[0];};})(jQuery);

//// JQUERY PLUGIN - MOUSE WHEEL
(function($){$.fn.extend({mousewheel:function(f){if(!f.guid)f.guid=$.event.guid++;if(!$.event._mwCache)$.event._mwCache=[];return this.each(function(){if(this._mwHandlers)return this._mwHandlers.push(f);else this._mwHandlers=[];this._mwHandlers.push(f);var s=this;this._mwHandler=function(e){e=$.event.fix(e||window.event);$.extend(e,this._mwCursorPos||{});var delta=0,returnValue=true;if(e.wheelDelta)delta=e.wheelDelta/120;if(e.detail)delta=-e.detail/3;if(window.opera)delta=-e.wheelDelta;for(var i=0;i<s._mwHandlers.length;i++)if(s._mwHandlers[i])if(s._mwHandlers[i].call(s,e,delta)===false){returnValue=false;e.preventDefault();e.stopPropagation();}return returnValue;};if($.browser.mozilla&&!this._mwFixCursorPos){this._mwFixCursorPos=function(e){this._mwCursorPos={pageX:e.pageX,pageY:e.pageY,clientX:e.clientX,clientY:e.clientY};};$(this).bind('mousemove',this._mwFixCursorPos);}if(this.addEventListener)if($.browser.mozilla)this.addEventListener('DOMMouseScroll',this._mwHandler,false);else this.addEventListener('mousewheel',this._mwHandler,false);else this.onmousewheel=this._mwHandler;$.event._mwCache.push($(this));});},unmousewheel:function(f){return this.each(function(){if(f&&this._mwHandlers){for(var i=0;i<this._mwHandlers.length;i++)if(this._mwHandlers[i]&&this._mwHandlers[i].guid==f.guid)delete this._mwHandlers[i];}else{if($.browser.mozilla&&!this._mwFixCursorPos)$(this).unbind('mousemove',this._mwFixCursorPos);if(this.addEventListener)if($.browser.mozilla)this.removeEventListener('DOMMouseScroll',this._mwHandler,false);else this.removeEventListener('mousewheel',this._mwHandler,false);else this.onmousewheel=null;this._mwHandlers=this._mwHandler=this._mwFixCursorPos=this._mwCursorPos=null;}});}});$(window).one('unload',function(){var els=$.event._mwCache||[];for(var i=0;i<els.length;i++)els[i].unmousewheel();});})(jQuery);

//// JQUERY PLUGIN - SCROLL TO JOEL
(function($){eval(function(p,a,c,k,e,d){e=function(c){return(c<a?"":e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('3.R=6(e){7 l=0;7 t=0;7 w=3.a(3.X(e,\'1e\'));7 h=3.a(3.X(e,\'1f\'));7 m=e.L;7 B=e.F;1a(e.S){l+=e.T+(e.8?3.a(e.8.W):0);t+=e.V+(e.8?3.a(e.8.10):0);e=e.S}l+=e.T+(e.8?3.a(e.8.W):0);t+=e.V+(e.8?3.a(e.8.10):0);c{x:l,y:t,w:w,h:h,m:m,B:B}};3.1d=6(e){b(e){w=e.k;h=e.C}f{w=(d.Y)?d.Y:(1.4&&1.4.k)?1.4.k:1.9.L;h=(d.H)?d.H:(1.4&&1.4.C)?1.4.C:1.9.F}c{w:w,h:h}};3.U=6(e){b(e){t=e.i;l=e.A;w=e.r;h=e.D}f{b(1.4&&1.4.i){t=1.4.i;l=1.4.A;w=1.4.r;h=1.4.D}f b(1.9){t=1.9.i;l=1.9.A;w=1.9.r;h=1.9.D}}c{t:t,l:l,w:w,h:h}};3.a=6(v){v=12(v);c 14(v)?0:v};3.16.E=6(s){o=3.17(s);c u.18(6(){n 3.P.E(u,o)})};3.P.E=6(e,o){7 z=u;z.o=o;z.e=e;z.p=3.R(e);z.s=3.U();z.J=6(){1b(z.j);z.j=1c};z.t=(n N).Z();z.M=6(){7 t=(n N).Z();7 p=(t-z.t)/z.o.I;b(t>=z.o.I+z.t){z.J();11(6(){z.q(z.p.y,z.p.x)},13)}f{G=((-g.O(p*g.Q)/2)+0.5)*(z.p.y-z.s.t)+z.s.t;K=((-g.O(p*g.Q)/2)+0.5)*(z.p.x-z.s.l)+z.s.l;z.q(G,K)}};z.q=6(t,l){d.19(l,t)};z.j=15(6(){z.M()},13)};',62,78,'|document||jQuery|documentElement||function|var|currentStyle|body|intval|if|return|window||else|Math||scrollTop|timer|clientWidth||wb|new|||scroll|scrollWidth|||this||||||scrollLeft|hb|clientHeight|scrollHeight|ScrollTo|offsetHeight|st|innerHeight|duration|clear|sl|offsetWidth|step|Date|cos|fx|PI|getPos|offsetParent|offsetLeft|getScroll|offsetTop|borderLeftWidth|css|innerWidth|getTime|borderTopWidth|setTimeout|parseInt||isNaN|setInterval|fn|speed|each|scrollTo|while|clearInterval|null|getClient|width|height'.split('|'),0,{}));})(jQuery);

(function($) {                                          // Compliant with jquery.noConflict()
$.fn.jCarouselLite = function(o) {
    o = $.extend({
        btnPrev: null,
        btnNext: null,
        btnGo: null,
        mouseWheel: false,
        auto: null,

        speed: 200,
        easing: null,

        vertical: false,
        circular: true,
        visible: 3,
        start: 0,
        scroll: 1,

        beforeStart: null,
        afterEnd: null
    }, o || {});

    return this.each(function() {                           // Returns the element collection. Chainable.

        var running = false, animCss=o.vertical?"top":"left", sizeCss=o.vertical?"height":"width";
        var div = $(this), ul = $("ul", div), tLi = $("li", ul), tl = tLi.size(), v = o.visible;

        if(o.circular) {
            ul.prepend(tLi.slice(tl-v-1+1).clone())
              .append(tLi.slice(0,v).clone());
            o.start += v;
        }

        var li = $("li", ul), itemLength = li.size(), curr = o.start;
        div.css("visibility", "visible");

        li.css({overflow: "hidden", float: o.vertical ? "none" : "left"});
        ul.css({margin: "0", padding: "0", position: "relative", "list-style-type": "none", "z-index": "1"});
        div.css({overflow: "hidden", position: "relative", "z-index": "2", left: "0px"});

        var liSize = o.vertical ? height(li) : width(li);   // Full li size(incl margin)-Used for animation
        var ulSize = liSize * itemLength;                   // size of full ul(total length, not just for the visible items)
        var divSize = liSize * v;                           // size of entire div(total length for just the visible items)

        li.css({width: li.width(), height: li.height()});
        ul.css(sizeCss, ulSize+"px").css(animCss, -(curr*liSize));

        div.css(sizeCss, divSize+"px");                     // Width of the DIV. length of visible images

        if(o.btnPrev)
            $(o.btnPrev).click(function() {
                return go(curr-o.scroll);
            });

        if(o.btnNext)
            $(o.btnNext).click(function() {
                return go(curr+o.scroll);
            });

        if(o.btnGo)
            $.each(o.btnGo, function(i, val) {
                $(val).click(function() {
                    return go(o.circular ? o.visible+i : i);
                });
            });

        if(o.mouseWheel && div.mousewheel)
            div.mousewheel(function(e, d) {
                return d>0 ? go(curr-o.scroll) : go(curr+o.scroll);
            });

        if(o.auto)
            setInterval(function() {
                go(curr+o.scroll);
            }, o.auto+o.speed);

        function vis() {
            return li.slice(curr).slice(0,v);
        };

        function go(to) {
            if(!running) {

                if(o.beforeStart)
                    o.beforeStart.call(this, vis());

                if(o.circular) {            // If circular we are in first or last, then goto the other end
                    if(to<=o.start-v-1) {           // If first, then goto last
                        ul.css(animCss, -((itemLength-(v*2))*liSize)+"px");
                        // If "scroll" > 1, then the "to" might not be equal to the condition; it can be lesser depending on the number of elements.
                        curr = to==o.start-v-1 ? itemLength-(v*2)-1 : itemLength-(v*2)-o.scroll;
                    } else if(to>=itemLength-v+1) { // If last, then goto first
                        ul.css(animCss, -( (v) * liSize ) + "px" );
                        // If "scroll" > 1, then the "to" might not be equal to the condition; it can be greater depending on the number of elements.
                        curr = to==itemLength-v+1 ? v+1 : v+o.scroll;
                    } else curr = to;
                } else {                    // If non-circular and to points to first or last, we just return.
                    if(to<0 || to>itemLength-v) return;
                    else curr = to;
                }                           // If neither overrides it, the curr will still be "to" and we can proceed.

                running = true;

                ul.animate(
                    animCss == "left" ? { left: -(curr*liSize) } : { top: -(curr*liSize) } , o.speed, o.easing,
                    function() {
                        if(o.afterEnd)
                            o.afterEnd.call(this, vis());
                        running = false;
                    }
                );
                // Disable buttons when the carousel reaches the last/first, and enable when not
                if(!o.circular) {
                    $(o.btnPrev + "," + o.btnNext).removeClass("disabled");
                    $( (curr-o.scroll<0 && o.btnPrev)
                        ||
                       (curr+o.scroll > itemLength-v && o.btnNext)
                        ||
                       []
                     ).addClass("disabled");
                }

            }
            return false;
        };
    });
};

function css(el, prop) {
    return parseInt($.css(el[0], prop)) || 0;
};
function width(el) {
    return  el[0].offsetWidth + css(el, 'marginLeft') + css(el, 'marginRight');
};
function height(el) {
    return el[0].offsetHeight + css(el, 'marginTop') + css(el, 'marginBottom');
};

})(jQuery);




//// GANESH PLUGIN - LAVALAMP
(function($){$.fn.lavaLamp=function(o){o=$.extend({fx:"linear",speed:500,click:function(){}},o||{});return this.each(function(){var b=$(this),noop=function(){},$back=$('<li class="back"><div class="left"></div></li>').appendTo(b),$li=$("li",this),curr=$("li.current",this)[0]||$($li[0]).addClass("current")[0];$li.not(".back").hover(function(){move(this)},noop);$(this).hover(noop,function(){move(curr)});$li.click(function(e){setCurr(this);return o.click.apply(this,[e,this])});setCurr(curr);function setCurr(a){$back.css({"left":a.offsetLeft+"px","width":a.offsetWidth+"px"});curr=a};function move(a){$back.each(function(){$(this).dequeue()}).animate({width:a.offsetWidth,left:a.offsetLeft},o.speed,o.fx)}})}})(jQuery);

