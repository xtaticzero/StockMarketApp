/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.loggin;

import com.xtaticzero.systems.base.constants.excepcion.BusinessException;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.logging.UserLogginService;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.vista.BaseAbstractMB;

/**
 *
 * @author xtati
 */
@Controller("userLoginView")
@Scope(value = "view")
public class UserLoginView extends BaseAbstractMB {

    private static final long serialVersionUID = 2316623966899405140L;
    @Autowired
    @Qualifier("userLogginService")
    private UserLogginService logginService;

    private UsuarioDTO usuario;

    @PostConstruct
    private void init() {
        logger.info("Loggin page");
        usuario = new UsuarioDTO();
    }

    public void login(ActionEvent event) {
        FacesMessage message = null;
        boolean loggedIn = false;

        if (usuario.getDisplay_name() != null && usuario.getPassword() != null) {

            try {
                UsuarioDTO correctUser = logginService.logginUser(usuario);
                if (correctUser != null) {
                    loggedIn = true;
                    setUserProfile(usuario);
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(
                                ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getContextPath()
                                + "/pages/inicio.html");
                    } catch (Exception e) {
                        System.err.println("error al redireccionar" + e.getMessage());
                    }
                }
            } catch (BusinessException ex) {
                logger.error(ex.getMessage(), ex);
                addErrorMessage("Usuario o Password incorrectos");
            }

        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
