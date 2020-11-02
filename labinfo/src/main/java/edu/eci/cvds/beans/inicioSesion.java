package edu.eci.cvds.beans;

import java.util.Enumeration;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
* Gestionamos el inicio de sesión del usuario
*/
public class inicioSesion
{
	private static HttpSession sesionHttp;
	private static Boolean sesionActiva;
	
	public static void inicioSesion(FacesContext fc)
	{
		sesionHttp = (HttpSession) fc.getExternalContext().getSession(false);
	}
	
	/**
	* Iniciamos sesión y gestionamos los datos para el inicio de sesión
	* @param nombre
	* @param objeto
	*/
	public static void setDatosSesion(String nombre, Object objeto)
	{
		try{
			if(sesionHttp.getId() != null && !sesionHttp.getId().isEmpty())
			{
				sesionActiva = true;
				sesionHttp.setAttribute(nombre, objeto);
				sesionHttp.setAttribute("sesionActiva", sesionActiva);
			}
			else
			{
				sesionActiva = false;
				sesionHttp.setAttribute("sesionActiva", sesionActiva);
				throw new Exception("Error al iniciar sesión");
			}
		} catch(Exception e)
		{
			sesionHttp.invalidate();
			e.printStackTrace();
		}
	}
	
	/*
	* Cerramos la sesión actual
	*/
	public static void cerrarSesion()
	{
		Enumeration<String> atributos = null;
		
		try{
			if(sesionHttp != null && sesionHttp.getId() != null && !sesionHttp.getId().isEmpty())
			{
				sesionActiva = false;
				atributos = sesionHttp.getAttributeNames();
				
				while(atributos.hasMoreElements())
				{
					String atributo = atributos.nextElement();
					sesionHttp.removeAttribute(atributo);
				}
				
				sesionHttp.invalidate();
			}
			else
			{
				throw new Exception("Error al cerrar la sesión");
			}
		} catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	* Obtenemos los datos del usuario que se está autenticando
	* @param dato
	* @return objeto
	*/
	public static void getDatosSesion(String dato)
	{
		if(sesionHttp != null && sesionHttp.getId() != null && !sesionHttp.getId().isEmpty())
		{
			sesionHttp.setAttribute("sesionActiva", sesionActiva);
		}
	}

	/*
	* Verificar el estado de la sesión dle usuario
	* @return activo
	*/
	public static Boolean getEstadoSesion()
	{
		boolean activo = false;
		
		if (sesionHttp != null && sesionHttp.getId() != null && !sesionHttp.getId().isEmpty() && sesionHttp.getAttribute("sesionActiva") != null) {
            activo = Boolean.parseBoolean(sesionHttp.getAttribute("sesionActiva").toString());
        }
		
		return activo;
	}
}