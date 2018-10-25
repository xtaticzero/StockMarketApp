/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.FrontException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.vista.CotizacionAbstractMB;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("cotizacionesMB")
@Scope(value = "view")
public class CotizacionesMB extends CotizacionAbstractMB {

    private static final long serialVersionUID = 1406891739814666634L;

    @PostConstruct
    public void init() throws FrontException, BusinessException, IOException {

        validateUsuarioValido();
        setCotizacionBO(getCotizacionService().getBOCotizacion(getUsuario()));
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/plantillas/CargaIPCPlantilla.xlsx");
        setPlantilla(new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "layoutIPC.xlsx"));
        setCotizacionBO(getCotizacionService().getLstIPC(getCotizacionBO(), 0));
        getCotizacionService().getLstCotizaciones(getCotizacionBO());
        getCotizacionBO().setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
        setLstIndiceCotizacion(getCotizacionBO().getLstIpc());

        setExistencias(new ArrayList());
        setUtilidades(new ArrayList());
        setLstCotizacionDTO(new ArrayList());
        setLstCotizacionAcciones(new ArrayList());

    }

    public void handleFileUpload(FileUploadEvent event) {
        try {

            UploadedFile fileExcel = event.getFile();

            getCotizacionService().insertIPCFromExcel(getCotizacionBO(), null, fileExcel.getInputstream());
            setCotizacionBO(getCotizacionService().getLstIPC(getCotizacionBO(), 0));
            setLstIndiceCotizacion(getCotizacionBO().getLstIpc());

            FacesMessage message = new FacesMessage(getMessageResourceString("msj.exito.carga.excel"), event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error(e);
            FacesMessage message = new FacesMessage(getMessageResourceString("msj.err.carga.excel"));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cotizacion Editada", ((CotizacionDiariaDTO) event.getObject()).getEmisora().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            getCotizacionBO().setCotizacionSeleccionada(((CotizacionDiariaDTO) event.getObject()));
            getCotizacionService().actualizarCotizacion(getCotizacionBO());
            getCotizacionService().getLstCotizaciones(getCotizacionBO());
        } catch (Exception e) {
            logger.error(e);
            msgError(e.getMessage());
        }
    }

    public void buscarHistorialCotizacion() {
        if (getCotizacionBO().getCotizacionSeleccionada() != null) {
            try {
                getCotizacionService().findCotizacionDiariaHistoricoByEmisora(getCotizacionBO());
            } catch (BusinessException ex) {
                getCotizacionBO().setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void onRowCancel(RowEditEvent event) {
        try {
            getCotizacionService().getLstCotizaciones(getCotizacionBO());
        } catch (Exception e) {
            logger.error(e);
            msgError(e.getMessage());
        }
    }

}
