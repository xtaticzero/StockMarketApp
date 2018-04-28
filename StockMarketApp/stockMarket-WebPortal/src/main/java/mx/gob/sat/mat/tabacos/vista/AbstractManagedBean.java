package mx.gob.sat.mat.tabacos.vista;

import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;

import javax.faces.context.FacesContext;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mx.gob.sat.mat.tabacos.constants.Constantes;
import mx.gob.sat.mat.tabacos.constants.enums.FileExtensionEnum;
import mx.gob.sat.mat.tabacos.constants.enums.MIMETypesEnum;
import mx.gob.sat.mat.tabacos.modelo.dto.AccesoUsr;

import org.apache.log4j.Logger;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public abstract class AbstractManagedBean implements Serializable {

    private static final long serialVersionUID = -954717909755577329L;

    /**
     * Objeto del log.
     */
    private final Logger log;
    private HttpSession session;

    /**
     * The constant factory.
     */
    private final PodamFactory factory = new PodamFactoryImpl();

    public static final String ERROR = "Error";
    public static final String INFO = "Información";
    public static final String WARN = "Atención";
    public static final String FATAL = "Error grave";

    /**
     * pistaAuditoriaService es la referencia del servicio de Pistas de
     * Auditoria.
     */
    public AbstractManagedBean() {
        log = Logger.getLogger(getClass());
    }

    public HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public boolean isDevelop() {
        ServletContext servletContext
                = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        return Boolean.parseBoolean(servletContext.getInitParameter(Constantes.SCOOP_DEV));
    }

    public void validaAccesoProceso(String identificadorProceso) {
        try {

        } catch (Exception e) {
            log.error("Error, acceso denegado: ", e);
            redireccionarPorOperacionDenegada(e);
        }
    }

    public void redireccionarPorOperacionDenegada(Exception e) {
        HttpSession sessionRedirect;
        sessionRedirect = getSession();

        sessionRedirect.setAttribute("mensaje", e);

        ServletContext dir = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("detalleError", e.getCause());

        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(dir.getContextPath()
                    + Constantes.PAGINA_ADD_ENCONSTRUCCION_ERROR);
        } catch (IOException ioe) {
            log.error("Error al redireccionar: ", e);
        }
    }

    public String getRFCSession() {
        String rfc = "";

        return rfc;
    }

    public AccesoUsr getAccesoUsr() {
        AccesoUsr accesoUsr;
        accesoUsr = new AccesoUsr();
        return accesoUsr;
    }

    public HttpSession getSession() {
        session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

        return session;
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
            getLogger().error(e.getMessage());
            AbstractManagedBean.msgFatal(error);
        }

    }

    public void createMessage(String summary, String detail,
            FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void addMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_INFO);
    }

    public void addErrorMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_ERROR);
    }

    public void addFatalMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_FATAL);
    }

    public void addWarningMessage(String titulo, String mensaje) {
        createMessage(titulo, mensaje, FacesMessage.SEVERITY_WARN);
    }

    /**
     * Agrega un mensaje de error sin detalle.
     *
     * @param clientId, identificador del componente.
     * @param msg mensaje a mostrar.
     */
    public void msgError(String clientId, String msg) {
        FacesContext.getCurrentInstance()
                .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
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
        String value;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(context, "tbcMsj");

        value = formatMessage(bundle, key, params);
        return value;
    }

    private static String formatMessage(ResourceBundle bundle, String key, Object... params) {
        String text;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
        }
        if (params != null) {
            MessageFormat mf = new MessageFormat(text);
            text = mf.format(params, new StringBuffer(), null).toString();
        }
        return text;
    }

    public String getRequestURL() {
        Object request = FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request instanceof HttpServletRequest) {
            return ((HttpServletRequest) request).getRequestURL().toString();
        } else {
            return "";
        }
    }

    public Logger getLogger() {
        return log;
    }

    public PodamFactory getFactory() {
        return factory;
    }

}
