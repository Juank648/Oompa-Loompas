package edu.eci.cvds.DAO;

import edu.eci.cvds.entities.Usuario;

public interface UsuarioDAO 
{

	Usuario getUsuario(String correo);

	void registrarUsuario(String nombre, boolean estado, String correo, String contra, int carnet);

}