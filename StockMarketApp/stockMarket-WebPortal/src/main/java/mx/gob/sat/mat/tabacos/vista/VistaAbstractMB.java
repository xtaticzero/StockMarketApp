/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.mat.tabacos.vista;

import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.constants.FileExtensionEnum;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import stock.vista.ConstantesVista;
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

//    @Autowired
//    @Qualifier("userLogginService")
//    private BaseBusinessServices userLogginService;
    public VistaAbstractMB() {
        logger = Logger.getLogger(getClass());
    }

    protected void validateUsuarioValido() throws IOException {
        if (getUserProfile() != null) {
            usuario = getUserProfile();
        } else {
            FacesContext.getCurrentInstance().getExternalContext().redirect(
                    ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext()).getContextPath()
                    + "/error/indexError.jsf");
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
