/**
 * 
 */

var n0=1;
	var n1=1;
	var n2;
	
for (var i=3; i<=11; i++) {
	n2=n0+n1;
	n0=n1;
	n1=n2;
}
	
	
	document.getElementById("hello").innerHTML = "Hello, world!" + " " + n2;
	