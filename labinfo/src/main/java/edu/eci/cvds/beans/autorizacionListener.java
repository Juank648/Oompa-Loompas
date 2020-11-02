package edu.eci.cvds.beans;
 
import java.io.IOException; 
import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import edu.eci.cvds.beans.inicioSesion;;
 
/**
 * Listener de autorización. Esta clase se encarga de verificar si la sesión 
 * esta activa para que el usuario pueda navegar por una aplicación. Si el 
 * usuario no tiene una sesión activa, la clase lo redirige a la página de 
 * inicio de la aplicacón.
 */
public class autorizacionListener implements PhaseListener 
{
    private static final long serialVersionUID = 1L;
	private inicioSesion sesion = null;
 
    @Override
    public void afterPhase(PhaseEvent event) {
        try {
            String paginaActual = event.getFacesContext().getViewRoot().getViewId();
            sesion.inicioSesion(event.getFacesContext());
             
            if (!paginaActual.contains("index.xhtml") && sesion.getEstadoSesion() == false) {               
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml?faces-redirect=true");
     
                NavigationHandler nh = event.getFacesContext().getApplication().getNavigationHandler();
                nh.handleNavigation(event.getFacesContext(), null, "index");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    @Override
    public void beforePhase(PhaseEvent event) {}
 
    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RESTORE_VIEW;
    }
}