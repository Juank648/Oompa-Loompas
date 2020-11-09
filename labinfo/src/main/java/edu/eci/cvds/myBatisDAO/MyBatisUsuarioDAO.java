package edu.eci.cvds.myBatisDAO;

import com.google.inject.Inject;
import edu.eci.cvds.DAO.UsuarioDAO;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.mappers.UsuarioMapper;

public class MyBatisUsuarioDAO implements UsuarioDAO{
	@Inject
	private UsuarioMapper mapper;
	
	@Override
	public Usuario getUsuario(String correo) {
		return mapper.getUsuario(correo);
	}

	@Override
	public void registrarUsuario(String nombre, boolean estado, String correo, String contrasena, int carnet) 
	{
		mapper.registrarUsuario(nombre, estado, correo, contrasena, carnet);
	}

}
