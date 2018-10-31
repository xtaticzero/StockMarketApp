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
import com.xtaticzero.systems.base.dto.CotizacionPromedioDTO;
import com.xtaticzero.systems.base.util.FechaUtil;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
//@Scope(value = "session")
@Scope(value = "view")
public class CotizacionesMB extends CotizacionAbstractMB {

    private static final long serialVersionUID = 1406891739814666634L;

    protected static String ATTRIB_YEAR_FILTER = "ATTRIB_YEAR_FILTER";
    protected static int MAX_OF_YEAR = 20;

    @PostConstruct
    public void init() throws FrontException, BusinessException, IOException {

        validateUsuarioValido();
        setCotizacionBO(getCotizacionService().getBOCotizacion(getUsuario()));
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/plantillas/CargaIPCPlantilla.xlsx");
        setPlantilla(new DefaultStreamedContent(stream, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "layoutIPC.xlsx"));
        InputStream stream2 = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/plantillas/CargaCotizacionDiariaPlantilla.xlsx");
        setPlantillaCotDiaria(new DefaultStreamedContent(stream2, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "layoutCotizacionDiaria.xlsx"));
        setCotizacionBO(getCotizacionService().getLstIPC(getCotizacionBO(), 0));
        getCotizacionService().getLstCotizaciones(getCotizacionBO());
        getCotizacionBO().setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
        setLstIndiceCotizacion(getCotizacionBO().getLstIpc());

        setExistencias(new ArrayList());
        setUtilidades(new ArrayList());
        setLstCotizacionDTO(new ArrayList());
        setLstCotizacionAcciones(new ArrayList());

        try {
            if (((Integer) getAttributeSession(ATTRIB_YEAR_FILTER)) != null) {
                setYearFilter(((Integer) getAttributeSession(ATTRIB_YEAR_FILTER)));
                getCotizacionBO().setYearFiltro(getYearFilter());
                getCotizacionService().calcularPromediosAnuales(getCotizacionBO(), getCotizacionBO().getYearFiltro());
            } else {
                getCotizacionBO().setYearFiltro(-1);
                getCotizacionService().calcularPromediosAnuales(getCotizacionBO(), FechaUtil.getCurrentYear());
            }
        } catch (Exception e) {
            logger.error(e);
            FacesMessage message = new FacesMessage(getMessageResourceString("msj.err.carga.datos"));
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

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

    public void handleFileUploadCotizacion(FileUploadEvent event) {
        try {

            UploadedFile fileExcel = event.getFile();

            getCotizacionService().getLstCotizacionesFromExcel(getCotizacionBO(), fileExcel.getInputstream());
            getCotizacionService().insertLstCotizacionesFromExcel(getCotizacionBO());
            getCotizacionService().getLstCotizaciones(getCotizacionBO());

            try {
                if (((Integer) getAttributeSession(ATTRIB_YEAR_FILTER)) != null) {
                    setYearFilter(((Integer) getAttributeSession(ATTRIB_YEAR_FILTER)));
                    getCotizacionBO().setYearFiltro(getYearFilter());
                    getCotizacionService().calcularPromediosAnuales(getCotizacionBO(), getCotizacionBO().getYearFiltro());
                } else {
                    getCotizacionBO().setYearFiltro(-1);
                    getCotizacionService().calcularPromediosAnuales(getCotizacionBO(), FechaUtil.getCurrentYear());
                }
            } catch (Exception e) {
                logger.error(e);

            }

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

    public List<Integer> getLstComboYears() {
        try {
            getCotizacionService().getLstOfYearsHistory(getCotizacionBO());
            return getCotizacionBO().getLstYearsHistorico() == null ? new ArrayList<Integer>() : getCotizacionBO().getLstYearsHistorico();
        } catch (Exception e) {
            logger.error(e);
            msgError(e.getMessage());
            return new ArrayList<>();
        }
    }

    public void processAverage() {
        if (getCotizacionBO().getYearFiltro() != null) {
            int currenteYear = FechaUtil.getCurrentYear();
            setAttributeSession(ATTRIB_YEAR_FILTER, getCotizacionBO().getYearFiltro());
            //Procesar y sacar promedio
            try {
                getCotizacionService().calcularPromediosAnuales(getCotizacionBO(), currenteYear);
            } catch (Exception e) {
                logger.error(e);
                msgError(e.getMessage());
            }

        } else {
            deletAttributeSession(ATTRIB_YEAR_FILTER);
        }
    }

    public String getNombreEmisoraById(int id) {
        if (getCotizacionBO().getLstCotizacionesDiarias() != null && !getCotizacionBO().getLstCotizacionesDiarias().isEmpty()) {
            List<CotizacionDiariaDTO> response = getCotizacionBO().getLstCotizacionesDiarias();

            if (response != null) {
                for (CotizacionDiariaDTO cotizacion : response) {
                    if (cotizacion.getEmisora().getEmisora_id().intValue() == id) {
                        return cotizacion.getEmisora().getNombre();
                    }
                }
            }

        }

        return "";
    }

    public int getLastYear() {
        return FechaUtil.getCurrentYear() - 1;
    }

    public CotizacionPromedioDTO getCotizacionPromedio(int id) {
        return getCotizacionBO().getMapCotizacionPromedio().get(new BigInteger(String.valueOf(id)));
    }

    public double getPromedioLastYear1() {
        CotizacionPromedioDTO res = getCotizacionPromedio(1);

        if (res != null) {
            return res.getPromedioLastYear() != null ? res.getPromedioLastYear().floatValue() : 0.0;
        }

        return 0.0;
    }

    public double getPromedioManyYear1() {
        CotizacionPromedioDTO res = getCotizacionPromedio(1);

        if (res != null) {
            return res.getPromedioManyYear() != null ? res.getPromedioManyYear().floatValue() : 0.0;
        }

        return 0.0;
    }
}
