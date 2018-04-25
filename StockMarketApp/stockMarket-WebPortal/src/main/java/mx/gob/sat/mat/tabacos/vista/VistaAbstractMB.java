/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.mat.tabacos.vista;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import mx.gob.sat.mat.tabacos.constants.enums.FileExtensionEnum;
import org.apache.log4j.Logger;
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
    /**
     * The constant factory.
     */
    private final PodamFactory factory = new PodamFactoryImpl();

    public VistaAbstractMB() {
        logger = Logger.getLogger(getClass());
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

    public PodamFactory getFactory() {
        return factory;
    }

}
