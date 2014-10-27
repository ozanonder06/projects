$(document).ready(function() {

  //entire project is warpped up in a self invoking
  //anonymous functions
  (function() {
	
	window.App = {
		Models: {},
		Collections: {},
		Views: {}
	};
	
	window.template = function(id) {
		return _.template( $('#' + id).html() );
	};
	
	/****Models.Tasks****/
	App.Models.Task = Backbone.Model.extend({});
	
	/****Collections.Tasks****/
	App.Collections.Tasks = Backbone.Collection.extend({
		model: App.Models.Task
	}); 
	
	/****Views.Tasks****/
	App.Views.Tasks = Backbone.View.extend({
	
		tagName: 'ul',
		
		initialize: function() {
			//whener a new model is added to the collection
			//addOne function is called
			this.collection.on('add', this.addOne, this);
		},
			
		render: function() {
			this.collection.each(this.addOne, this);
			
			return this;
		},
		
		addOne: function(task) {
			//creating a new child view
			var taskView = new App.Views.Task({model: task});
			
			//append it to the root element
			this.$el.append(taskView.render().el);
		}
	
	});
	
	/****Views.Task****/
	App.Views.Task = Backbone.View.extend({
		tagName: 'li',
		
		template: template('taskTemplate'),
		
		initialize: function() {
			this.model.on('change', this.render, this);
			this.model.on('destroy', this.remove, this);
		},
		
		//listen the events when buttons are clicked
		events: {
			'click .edit': 'editTask',
			'click .delete': 'destroy' 
		},
		
		editTask: function() {
			var newTaskTitle = prompt('what would you like to change the text to?', this.model.get('title'));
			/* when model is set to new value, render function
   			   will be invoked as it was expressed in initialize*/
			if(!newTaskTitle) return;   
			this.model.set('title', newTaskTitle);
		},
		
		destroy: function() {
			this.model.destroy();
		},
		
		remove: function() {
			//get the root element of this single Task and remove
			this.$el.remove();
		},

		render: function() {
			var template = this.template(this.model.toJSON());
			this.$el.html(template);
			return this;
		}
	
	});
	
	/****Views.AddTask****/
	App.Views.AddTask = Backbone.View.extend({
	
		el: '#addTask',
		
		events: {
			'submit': 'submit'
		},
		
		initialize: function() {
		
		},
		
		submit: function(e) {
			e.preventDefault();
			
			//current targer is the form
			//find the input
			var newTaskTitle = $(e.currentTarget).find('input[type=text]').val();
			var task = new App.Models.Task({ title: newTaskTitle });
			this.collection.add(task);
		}
		
	});
	
    tasksCollection = new App.Collections.Tasks([
		{
			title: 'Go to the store',
			priority: 4
		},
		{
			title: 'Go to the Mall',
			priority: 3
		},
		{
			title: 'Run',
			priority: 2
		}		
	]);

	var addTaskView = new App.Views.AddTask({ collection: tasksCollection});
	var tasksView = new App.Views.Tasks({collection: tasksCollection});
	$('.tasks').html(tasksView.render().el);
	
 })();//anonymous function
 
});	//document ready


