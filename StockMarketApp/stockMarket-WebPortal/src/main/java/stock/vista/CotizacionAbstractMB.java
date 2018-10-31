/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista;

import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import stock.horizontal.dto.CotizacionDTO;
import stock.horizontal.dto.ExistenciaInicial;
import stock.horizontal.dto.Utilidad;
import stock.vista.model.IpcModel;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class CotizacionAbstractMB extends VistaAbstractMB {

    private static final long serialVersionUID = -2690236031783823971L;

    private StreamedContent plantilla;
    private StreamedContent plantillaCotDiaria;

    private Integer yearFilter;

    private List<ExistenciaInicial> existencias;
    private List<Utilidad> utilidades;
    private List<CotizacionDTO> lstCotizacionDTO;
    private List<CotizacionDTO> lstCotizacionAcciones;
    private List<IpcModel> lstIndiceCotizacion;

    private CotizacionVectorBO cotizacionBO;
    private List<CotizacionDiariaDTO> filteredCotizaciones;

    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;

    public StreamedContent getPlantilla() {
        return plantilla;
    }

    public StreamedContent getPlantillaCotDiaria() {
        return plantillaCotDiaria;
    }

    public Integer getYearFilter() {
        return yearFilter;
    }

    public void setYearFilter(Integer yearFilter) {
        this.yearFilter = yearFilter;
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

    public List<IpcModel> getLstIndiceCotizacion() {
        return lstIndiceCotizacion;
    }

    public void setLstIndiceCotizacion(List<IPCDto> lstIndiceCotizacion) {

        if (lstIndiceCotizacion != null && !lstIndiceCotizacion.isEmpty()) {
            this.lstIndiceCotizacion = new ArrayList<>();
            for (IPCDto iPCDto : lstIndiceCotizacion) {
                this.lstIndiceCotizacion.add(new IpcModel(iPCDto));
            }
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

    public void setPlantilla(StreamedContent plantilla) {
        this.plantilla = plantilla;
    }

    public void setPlantillaCotDiaria(StreamedContent plantillaCotDiaria) {
        this.plantillaCotDiaria = plantillaCotDiaria;
    }

}
