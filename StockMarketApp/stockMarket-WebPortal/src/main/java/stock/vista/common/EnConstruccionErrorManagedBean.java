/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.common;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.vista.ConstantesVista;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("enConstruccionErrorMB")
@Scope(value = "view")
public class EnConstruccionErrorManagedBean implements Serializable {

    private static final long serialVersionUID = -1302797543271187783L;

    private String error;

    /**
     * PostConstruct que obtiene la exception de session he imprimer el error.
     */
    @PostConstruct
    public void init() {
        FacesContext faces = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) faces.getExternalContext().getSession(true);
        if (session.getAttribute(ConstantesVista.MSG_ERROR_SESSION) != null) {
            Object obj = session.getAttribute(ConstantesVista.MSG_ERROR_SESSION);

            if (obj instanceof Exception) {
                Exception e = (Exception) session.getAttribute(ConstantesVista.MSG_ERROR_SESSION);
                setError(e.getMessage());
            } else {
                String e = (String) session.getAttribute(ConstantesVista.MSG_ERROR_SESSION);
                setError(e);
            }

            session.removeAttribute(ConstantesVista.MSG_ERROR_SESSION);
        }
    }

    /**
     * De tipo String.
     *
     * @param error Tipo de dato String.
     */
    public void setError(String error) {
        this.error = error;
    }

    /**
     * De tipo String.
     *
     * @return error.
     */
    public String getError() {
        return error;
    }

}
