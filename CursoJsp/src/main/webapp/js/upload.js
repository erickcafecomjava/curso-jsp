


var formFile = document.getElementById("foto");

formFile.addEventListener("change"  ,function(event) {
	
	var visualisadorFoto = document.getElementById(" preview");
	
	visualisadorFoto.src = URL.createObjectURL(event.target.files[0]);
	window.console.log(event.target.files[0])
	visualisadorFoto.addEventListener("load" , function() {
		URL.revokeObjectURL(visualisadorFoto.src);


	});
});



	