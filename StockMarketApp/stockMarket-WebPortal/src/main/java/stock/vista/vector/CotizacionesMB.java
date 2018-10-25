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
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import stock.vista.VistaAbstractMB;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.horizontal.dto.CotizacionDTO;
import stock.horizontal.dto.ExistenciaInicial;
import stock.horizontal.dto.Utilidad;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("cotizacionesMB")
@Scope(value = "view")
public class CotizacionesMB extends VistaAbstractMB {

    private static final long serialVersionUID = 1406891739814666634L;
    
    private StreamedContent plantilla;

    private List<ExistenciaInicial> existencias;
    private List<Utilidad> utilidades;
    private List<CotizacionDTO> lstCotizacionDTO;
    private List<CotizacionDTO> lstCotizacionAcciones;
    private List<IPCDto> lstIndiceCotizacion;

    private CotizacionVectorBO cotizacionBO;
    private List<CotizacionDiariaDTO> filteredCotizaciones;

    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;

    public CotizacionesMB() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/plantillas/CargaIPCPlantilla.xlsx");
        plantilla = new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "plantilla.xlsx");
    }
    
    

    @PostConstruct
    public void init() throws FrontException, BusinessException {

        validateUsuarioValido();
        cotizacionBO = cotizacionService.getBOCotizacion(getUsuario());
        cotizacionBO = cotizacionService.getLstIPC(cotizacionBO, 0);
        cotizacionService.getLstCotizaciones(cotizacionBO);
        cotizacionBO.setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
        lstIndiceCotizacion = cotizacionBO.getLstIpc();

        existencias = new ArrayList<>();
        utilidades = new ArrayList<>();
        lstCotizacionDTO = new ArrayList<>();
        lstCotizacionAcciones = new ArrayList<>();

    }

    public void handleFileUpload(FileUploadEvent event) {
        try {

            UploadedFile fileExcel = event.getFile();

            cotizacionService.insertIPCFromExcel(cotizacionBO, null, fileExcel.getInputstream());
            cotizacionBO = cotizacionService.getLstIPC(cotizacionBO, 0);
            lstIndiceCotizacion = cotizacionBO.getLstIpc();

            FacesMessage message = new FacesMessage(getMessageResourceString("msj.exito.carga.excel"), event.getFile().getFileName());
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            logger.error(e);
            FacesMessage message = new FacesMessage(getMessageResourceString("msj.err.carga.excel"));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public List<ExistenciaInicial> getExistencias() {
        return existencias;
    }

    public void setExistencias(List<ExistenciaInicial> existencias) {
        this.existencias = existencias;
    }

    public List<Utilidad> getUtilidades() {
        return utilidades;
    }

    public void setUtilidades(List<Utilidad> utilidades) {
        this.utilidades = utilidades;
    }

    public List<CotizacionDTO> getLstCotizacionDTO() {
        return lstCotizacionDTO;
    }

    public void setLstCotizacionDTO(List<CotizacionDTO> lstCotizacionDTO) {
        this.lstCotizacionDTO = lstCotizacionDTO;
    }

    public List<CotizacionDTO> getLstCotizacionAcciones() {
        return lstCotizacionAcciones;
    }

    public void setLstCotizacionAcciones(List<CotizacionDTO> lstCotizacionAcciones) {
        this.lstCotizacionAcciones = lstCotizacionAcciones;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cotizacion Editada", ((CotizacionDiariaDTO) event.getObject()).getEmisora().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);

        try {
            cotizacionBO.setCotizacionSeleccionada(((CotizacionDiariaDTO) event.getObject()));
            cotizacionService.actualizarCotizacion(cotizacionBO);
            cotizacionService.getLstCotizaciones(cotizacionBO);
        } catch (Exception e) {
            logger.error(e);
            msgError(e.getMessage());
        }
    }

    public void buscarHistorialCotizacion() {
        if (cotizacionBO.getCotizacionSeleccionada() != null) {
            try {
                cotizacionService.findCotizacionDiariaHistoricoByEmisora(cotizacionBO);
            } catch (BusinessException ex) {
                cotizacionBO.setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    public void onRowCancel(RowEditEvent event) {
        try {
            cotizacionService.getLstCotizaciones(cotizacionBO);
        } catch (Exception e) {
            logger.error(e);
            msgError(e.getMessage());
        }
    }
    
    
 
    public StreamedContent getPlantilla() {
        return plantilla;
    }
    
    

    public CotizacionVectorBO getCotizacionBO() {
        return cotizacionBO;
    }

    public void setCotizacionBO(CotizacionVectorBO cotizacionBO) {
        this.cotizacionBO = cotizacionBO;
    }

    public CotizacionDiariaService getCotizacionService() {
        return cotizacionService;
    }

    public void setCotizacionService(CotizacionDiariaService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    public List<CotizacionDiariaDTO> getFilteredCotizaciones() {
        return filteredCotizaciones;
    }

    public void setFilteredCotizaciones(List<CotizacionDiariaDTO> filteredCotizaciones) {
        this.filteredCotizaciones = filteredCotizaciones;
    }

    public List<IPCDto> getLstIndiceCotizacion() {
        return lstIndiceCotizacion;
    }

    public void setLstIndiceCotizacion(List<IPCDto> lstIndiceCotizacion) {
        this.lstIndiceCotizacion = lstIndiceCotizacion;
    }

}
