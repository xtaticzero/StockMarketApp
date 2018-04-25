/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.VistaAbstractMB;
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

    private List<ExistenciaInicial> existencias;
    private List<Utilidad> utilidades;
    private List<CotizacionDTO> lstCotizacionDTO;
    private List<CotizacionDTO> lstCotizacionAcciones;

    @PostConstruct
    public void init() {
        existencias = new ArrayList<>();
        utilidades = new ArrayList<>();
        lstCotizacionDTO = new ArrayList<>();
        lstCotizacionAcciones = new ArrayList<>();

        for (int i = 1; i <= 50; i++) {
            ExistenciaInicial existencia = getFactory().manufacturePojo(ExistenciaInicial.class);
            existencia.setEmisora("Emisora " + i);
            existencias.add(existencia);
        }

        for (int i = 1; i <= 50; i++) {
            Utilidad utilidad = getFactory().manufacturePojo(Utilidad.class);
            utilidad.setEmisora("Unidad " + i);
            utilidades.add(utilidad);
        }

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 12, 31, 0, 0);
        int contador = 1;
        while (true) {
            CotizacionDTO cotizacion = getFactory().manufacturePojo(CotizacionDTO.class);
            cotizacion.setId(contador);
            cotizacion.setEmisora("Emisora " + ++contador);
            addDay(calendar, cotizacion);
            cotizacion.setFecha(calendar.getTime());
            cotizacion.setCostoAdquisicion(Double.valueOf(cotizacion.getPorcentaje()) / 100);
            cotizacion.setIsRed(cotizacion.getPorcentaje() <= 50);
            lstCotizacionAcciones.add(cotizacion);
            if (calendar.get(Calendar.YEAR) == 2018) {
                break;
            }
        }
        calendar.set(2000, 12, 31, 0, 0);
        for (int i = 1; i <= 17; i++) {
            CotizacionDTO cotizacion = getFactory().manufacturePojo(CotizacionDTO.class);
            cotizacion.setEmisora("Emisora " + i);
            calendar.add(Calendar.YEAR, 1);
            cotizacion.setFecha(calendar.getTime());
            cotizacion.setId(i);
            cotizacion.setCostoAdquisicion(Double.valueOf(cotizacion.getPorcentaje()) / 100);
            cotizacion.setIsRed(cotizacion.getPorcentaje() <= 50);
            lstCotizacionDTO.add(cotizacion);
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

    private void addDay(Calendar calendar, CotizacionDTO cotizacion) {
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        while (true) {
            if ((calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)) {
                calendar.add(Calendar.DAY_OF_WEEK, 1);
            } else {
                break;
            }
        }

    }

}
