var listaUsuarios = JSON.parse(localStorage.getItem('listaDeUsuariosLs'));
function obtenerListaDeUsuarios(){
	

	if(listaUsuarios == null  ){
		listaUsuarios = [
			['jose','jose.castro@gmail.com','jose123'],['andres','andres.molina@gmail.com',
			'molina123']
		]
	}
	/*if(aa!= null) {
		listaUsuarios.push([a]);
	}*/

	return listaUsuarios;
}

function agregarUsuario(usuario, correo,contraseña){
	console.log('Entra agregrar');
	console.log(usuario);
	console.log(contraseña);
	console.log(correo);
	var listaUsuarios = obtenerListaDeUsuarios();

	listaUsuarios.push([usuario,correo,contraseña]);
	console.log('Ya agregó');
	console.log(listaUsuarios[2][0]);
}

function validarCredenciales(pCorreo, pContraseña){
	var listaUsuarios = obtenerListaDeUsuarios();
	var bAcceso = false;
	for(var i=0; i<listaUsuarios.length; i++){
		if(pCorreo == listaUsuarios[i][0] && pContraseña == listaUsuarios[i][2]){
			bAcceso = true;

		
			
		}

	}
	
	

	return bAcceso;

}



$('.botonCuenta').click(function(){
    $('.formulario').animate({
        height: "toggle",
        'padding-top': 'toggle',
        'padding-bottom': 'toggle'
    }, "slow");
});


document.querySelector('#boton').addEventListener('click',iniciarSesion);



function iniciarSesion(){
	var mensaje = 'hola';
	var sCorreo = '';
	var sContraseña = '';
	var bAcceso = false;

	sCorreo = document.querySelector('#atributo').value;
	sContraseña = document.querySelector('#contra').value;

	bAcceso = validarCredenciales(sCorreo,sContraseña);
	
	if(bAcceso==true){
		alert('Bienvenido'+' '+sCorreo);
		window.location.href = 'pagina.html';
	}
	else{
		alert('Usuario o contraseña incorrecta')
	}
}


function volver(){
	console.log('Entra');
	window.location.href = 'index.html';
}

document.querySelector('#botonRegistro').addEventListener('click',registrarse);


function registrarse() {
	usuario = document.querySelector('#registoUsuario').value;
	correo = document.querySelector('#correo').value;
	contraseña = document.querySelector('#registoContra').value;

	agregarUsuario(usuario,correo,contraseña);
}