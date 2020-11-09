package edu.eci.cvds.mappers;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.entities.Usuario;

public interface UsuarioMapper 
{
	public Usuario getUsuario(@Param("correo") String correo);

	public void registrarUsuario(@Param("nombre")String nombre, @Param("estado")boolean estado, @Param("correo")String correo, @Param("contrasena") String contrasena, @Param("carnet") int carnet);
}