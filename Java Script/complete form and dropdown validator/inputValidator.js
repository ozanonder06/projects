'use strict';

function InputValidator() {
	
	this.isNumber = function(num) {
			return (num >= 0 && num <= 9);
	}
	
	this.isName = function(text) {
		for(var i=0; i<text.length; i++) {
			if(!this.isLetter(text.charAt(i)))
				return false;
		}
		return true;
	}
	
	this.isLetter = function(charValue) {
		if((charValue >= 'a' && charValue <= 'z') || (charValue >= 'A' && charValue <= 'Z'))
			return true;	
		else
			return false;
	}
	
	this.isPhoneNumber = function(num) {
		if(num.charAt(3) !== '-' || num.charAt(7) !== '-' || num.length !== 12)
			return false;
		for(var i=0; i<num.length; i++) {
			if(i==3 || i==7) {
				continue;
			} else {
			if(!this.isNumber(num.charAt(i)))
				return false;
			}	
		}	
		return true;		
	}
	
	/**
	 * e-mail verifier
	 * assumption: the format is name@example.com
	 * name can be a letter or number
	 * example and com must be only letters
	 * '@' proceeds '.' and can not be the first character
	 * '.' can not be the last character
	 */
	this.isEmail = function(text) {
		var parts = text.split('@');
		if(parts.length !== 2 || text.indexOf('@') == 0) {	
			return false;
		} 
		for(var i=0; i<parts[0].length; i++) {
			if(!this.isNumber(parts[0][i]) && !this.isLetter(parts[0][i]) )
				return false;
		}		
		var lastParts = parts[1].split('.');
		
		if(lastParts.length !== 2 || text.indexOf('.') == text.length-1) {
			return false;
		} 	
		for(var j=0; j<lastParts.length; j++) {		
			for(var k=0; k<lastParts[j].length; k++) {		
				if(!this.isLetter(lastParts[j][k])) {			
					return false;
				}
			}
		}	
		return true;
	}
}