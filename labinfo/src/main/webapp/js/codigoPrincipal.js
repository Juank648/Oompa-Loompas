
/*function volver() {
	console.log('Entra');
	window.location.href = 'index.html';
}

document.querySelector('#boton1').addEventListener('click',volver); */

var nav = document.getElementById('main-nav');
nav.addEventListener('clic',function(){
	console.log('Entraa js');
	nav.classList.toggle('mostrar'); 
});


$('.menu-bar').click(function(){
    $('.uno').animate({
        height: "toggle",
        'padding-top': 'toggle',
        'padding-bottom': 'toggle'
    }, "slow");
});
