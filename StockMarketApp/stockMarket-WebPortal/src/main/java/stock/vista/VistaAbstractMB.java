/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista;

import com.xtaticzero.systems.base.constants.excepcion.ExceptionConstant;
import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.FrontException;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.constants.FileExtensionEnum;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import stock.vista.constans.CatalogoErroresEnum;
import stock.vista.vector.enums.MIMETypesEnum;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class VistaAbstractMB implements Serializable {

    private static final long serialVersionUID = 9029639211570813033L;

    protected final Logger logger;
    private UsuarioDTO usuario;
    /**
     * The constant factory.
     */
    private final PodamFactory factory = new PodamFactoryImpl();

    public VistaAbstractMB() {
        logger = Logger.getLogger(getClass());
    }

    public void preRender() throws FrontException {
        if (usuario.getRol_id() == null) {
            BusinessException bEx = new BusinessException("usr.invalido", "usuario desconocido");
            FrontException fEx = new FrontException(ExceptionConstant.ERR_GENERAL, bEx, CatalogoErroresEnum.ERROR_USUARIO_INVALIDO.getTipo(), CatalogoErroresEnum.ERROR_USUARIO_INVALIDO.getCodigo());
            setAttributeSession(ConstantesVista.MSG_ERROR_SESSION, fEx);
            throw fEx;
        }
    }

    public FrontException errorEnVista(Exception frontException) throws BusinessException {
        String msgErr;
        if (frontException instanceof FrontException) {
            msgErr = ((FrontException) frontException).getMessage();
            setAttributeSession(ConstantesVista.MSG_ERROR_SESSION, msgErr);
            return ((FrontException) frontException);
        } else if (frontException instanceof BusinessException) {
            msgErr = ((BusinessException) frontException).getMessage();
            setAttributeSession(ConstantesVista.MSG_ERROR_SESSION, msgErr);
            throw ((BusinessException) frontException);
        }

        throw new UnsupportedOperationException("Excepcion no soportada");
    }

    protected void validateUsuarioValido() throws FrontException {
        usuario = getUserProfile();
        if (usuario != null && usuario.getUser_id() != null) {
            return;
        } else {
            BusinessException bEx = new BusinessException("usr.invalido", "usuario desconocido");
            FrontException fEx = new FrontException(ExceptionConstant.ERR_GENERAL, bEx, CatalogoErroresEnum.ERROR_USUARIO_INVALIDO.getTipo(), CatalogoErroresEnum.ERROR_USUARIO_INVALIDO.getCodigo());
            setAttributeSession(ConstantesVista.MSG_ERROR_SESSION, fEx);
            logger.error(fEx.getMessage(),fEx);
            throw fEx;
        }
    }

    /**
     * Obtiene el Nombre del usuario autenticado
     */
    public String getNameSession() {
        if (getUserProfile() != null) {
            return getUserProfile().getDisplay_name();
        }
        return null;
    }

    /**
     * Obtiene el UserProfile del usuario autenticado
     *
     * @return
     */
    public UsuarioDTO getUserProfile() {
        return (UsuarioDTO) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get(ConstantesVista.USER_PROFILE);
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public HttpSession getNewSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
    }

    public void setUserProfile(UsuarioDTO userProfile) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(ConstantesVista.USER_PROFILE, userProfile);
    }

    public void setAttributeSession(String nameAttrib, Object newAttrib) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(nameAttrib, newAttrib);
    }

    public void deletAttributeSession(String nameAttrib) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .remove(nameAttrib);
    }

    public void generaDocumento(byte[] arregloBytes, MIMETypesEnum mimeType, String nombre, FileExtensionEnum extencion) {
        if (arregloBytes != null) {
            despachaArchivo(arregloBytes, mimeType.getType(), nombre + extencion.getExtension(),
                    "Error al generar Archivo.");
        }
    }

    public void despachaArchivo(byte[] archivo, String contentType, String nombreArchivo, String error) {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().
                    getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setContentLength(archivo.length);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo + "\"");

            ServletOutputStream out;
            out = response.getOutputStream();
            out.write(archivo);
            FacesContext.getCurrentInstance().responseComplete();

        } catch (IOException e) {
            logger.error(e.getMessage());
            msgFatal(error);
        }

    }

    public void msgInfo(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", msg));
    }

    public static void msgFatal(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", msg));
    }

    public void msgWarn(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", msg));
    }

    /**
     * Agrega un mensaje de error sin detalle.
     *
     * @param msg mensaje a mostrar.
     */
    public void msgError(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", msg));
    }

    /**
     * Obtiene un mensaje del archivo de propiedades de una clave enviada.
     *
     * @param key clave a buscar.
     * @param params uno varios objetos que determinan el formato del mensaje.
     * @return String con el mensaje obtenido.
     */
    public String getMessageResourceString(String key, Object... params) {
        String value = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(
                context, "msj");

        value = formatMessage(bundle, key, params);
        return value;
    }

    private String formatMessage(ResourceBundle bundle, String key,
            Object... params) {
        String text = null;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
            logger.error("Error en ", e);
        }
        if (params != null && params.length > 0) {
            text = MessageFormat.format(text, params);
        }
        return text;
    }

    public PodamFactory getFactory() {
        return factory;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
