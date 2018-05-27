/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.loggin;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.logging.UserLogginService;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import stock.vista.VistaAbstractMB;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author xtati
 */
@Controller("userLoginView")
@Scope(value = "view")
public class UserLoginView extends VistaAbstractMB {

    private static final long serialVersionUID = 2316623966899405140L;
    @Autowired
    @Qualifier("userLogginService")
    private UserLogginService logginService;

    @PostConstruct
    protected void init() {
        setUsuario(new UsuarioDTO());
        if (getNameSession() != null) {
            logger.info("Usuario firmado : ".concat(getNameSession()));
        }
        setUserProfile(null);
        logger.info("Loggin page");
    }

    public void login(ActionEvent event) {
        boolean loggedIn = false;

        if (getUsuario().getDisplay_name() != null && getUsuario().getPassword() != null) {

            try {
                UsuarioDTO correctUser = logginService.logginUser(getUsuario());
                if (correctUser != null) {
                    loggedIn = true;
                    setUserProfile(correctUser);
                    try {
                        FacesContext.getCurrentInstance().getExternalContext().redirect(
                                ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getContextPath()
                                + "/pages/stockMarket/paginaInicio.jsf");
                    } catch (Exception e) {
                        logger.error(getMessageResourceString("msj.loggin.err"));
                        logger.error(e.getMessage(), e);

                    }
                }
            } catch (BusinessException ex) {
                logger.error(ex.getMessage(), ex);
                msgError("Usuario o Password incorrectos");
            }

        } else {
            loggedIn = false;

        }

        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

}
