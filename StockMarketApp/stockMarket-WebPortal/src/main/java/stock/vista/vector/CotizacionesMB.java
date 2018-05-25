/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.ExceptionConstant;
import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.FrontException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import stock.vista.constans.CatalogoErroresEnum;
import stock.vista.VistaAbstractMB;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.horizontal.dto.CotizacionDTO;
import stock.horizontal.dto.ExistenciaInicial;
import stock.horizontal.dto.Utilidad;
import stock.vista.ConstantesVista;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("cotizacionesMB")
@Scope(value = "view")
public class CotizacionesMB extends VistaAbstractMB {

    private static final long serialVersionUID = 1406891739814666634L;

    private List<ExistenciaInicial> existencias;
    private List<Utilidad> utilidades;
    private List<CotizacionDTO> lstCotizacionDTO;
    private List<CotizacionDTO> lstCotizacionAcciones;

    private CotizacionVectorBO cotizacionBO;
    private List<CotizacionDiariaDTO> filteredCotizaciones;

    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;

    @PostConstruct
    public void init() throws FrontException, BusinessException {

        validateUsuarioValido();
        cotizacionBO = cotizacionService.getBOCotizacion(getUsuario());
        cotizacionService.getLstCotizaciones(cotizacionBO);

        existencias = new ArrayList<>();
        utilidades = new ArrayList<>();
        lstCotizacionDTO = new ArrayList<>();
        lstCotizacionAcciones = new ArrayList<>();

    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
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

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cotizacion Cacela Edicion", ((CotizacionDiariaDTO) event.getObject()).getEmisora().getNombre());
        FacesContext.getCurrentInstance().addMessage(null, msg);
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

}
