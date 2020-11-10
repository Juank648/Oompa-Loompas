package edu.eci.cvds.beans;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import com.google.inject.Injector;
// Manejo de conexión web 
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
// Seguridad
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
// Clases
import edu.eci.cvds.servicios.ServiciosECILabImpl;
import edu.eci.cvds.entities.Usuario;

/**
* Gestionamos el inicio de sesión del usuario
*/
@ManagedBean(name = "inicioSesionBean")
public class InicioSesion extends BaseRegistroLabBean
{
	private String correo;
	private String contrasena;
	private Usuario usuario;
	private Injector injector;
	private ServiciosECILabImpl servicios; //Variable a revisar y clase a crear
	
	/**
	* Constructor para la gestión de inicio de sesión
	*/
	public InicioSesion()
	{
		injector = super.getInjector();
		//servicios = injector.getInstance(serviciosECILabImpl.class);
	}
	
	/**
	* Establecemos el correo del usuario
	* @param correo
	*/
	public void setCorreo(String correo)
	{
		this.correo = correo;
	}
	
	/**
	* Establecemos la contraseña del usuario
	* @param contrasena
	*/
	public void setContrasena(String contrasena)
	{
		this.contrasena = contrasena;
	}
	
	/**
	* Obtener el correo del usuario
	* @return correo
	*/
	public String getCorreo()
	{
		try
		{
			if(SecurityUtils.getSubject().getPrincipal() == null)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioAdministrador.xhtml");
			}
		} catch(IOException e)
		{
			e.printStackTrace();
		}
		return correo;
	}
	
	/**
	* Obtener la contraseña del usuario
	* @return contrasena
	*/
	public String getContrasena()
	{
		return contrasena;
	}
	
	/**
	* Iniciar sesión con el usuario
	*/
	public void iniciarSesion()
	{
		try
		{
			Subject usuarioActual = SecurityUtils.getSubject();
			String hexadecimal = new Sha256Hash(contrasena).toHex();
			System.out.println(hexadecimal);
			UsernamePasswordToken token = new UsernamePasswordToken(correo, hexadecimal);
			token.setRememberMe(true);
			usuarioActual.login(token);
			FacesContext.getCurrentInstance().getExternalContext().redirect("usuarioAdministrador.xhtml");
			
			if(usuario == null)
			{
				//usuario = servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString());
			}
		} catch(Exception e)
		{
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage("Error", "Por favor revise sus credenciales, usuario o contraseña incorrectos"));
		}
	}
	
	/**
	* Obtener el usuario que está en la sesión
	* @return usuario
	*/
	public Usuario getUsuario()
	{
		try 
		{
			if(SecurityUtils.getSubject().getPrincipal() == null)
			{
				FacesContext.getCurrentInstance().getExternalContext().redirect("iniciarSesion.xhtml");
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
		}
		if(usuario == null && SecurityUtils.getSubject().getPrincipal() != null)
		{
			//usuario = servicios.getUsuario(SecurityUtils.getSubject().getPrincipal().toString());
		}
		return usuario;
	}
	
	/**
	* Cerrar seión iniciada por el usuario
	*/
	public void cerrarSesion()
	{
		try
		{
			SecurityUtils.getSubject().logout();
			usuario = null;
			FacesContext.getCurrentInstance().getExternalContext().redirect("iniciarSesion.xhtml");
		} catch(Exception e)
		{
			e.printStackTrace();
		}	
	}
	
}