/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.enums.ReportsEnum;
import com.xtaticzero.systems.business.exception.ReporterJasperException;
import com.xtaticzero.systems.business.util.ReporterService;
import com.xtaticzero.systems.dao.PruebaDao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.constants.enums.FileExtensionEnum;
import mx.gob.sat.mat.tabacos.vista.VistaAbstractMB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.horizontal.dto.CotizacionDTO;
import stock.vista.vector.enums.MIMETypesEnum;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("estatusOrdenesMB")
@Scope(value = "view")
public class EstatusOrdenesMB extends VistaAbstractMB {

    private static final long serialVersionUID = -8723799854189735209L;

    @Autowired
    @Qualifier("pruebaDao")
    private PruebaDao pruebaDao;

    @Autowired
    @Qualifier("reporterService")
    private ReporterService reporterService;

    private List<CotizacionDTO> lstCotizacionDTO;
    private CotizacionDTO seleccionado;
    private String nombreArchivo;

    @PostConstruct
    public void init() {
        lstCotizacionDTO = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        calendar.set(2000, 12, 31, 0, 0);
        int contador = 1;

        if (pruebaDao != null) {
            System.out.println("todo ok");
            System.out.println("Imprime resultado de la base " + pruebaDao.getTime());
            logger.info("Imprime resultado de la base " + pruebaDao.getTime());
        } else {
            System.err.println("Valio ");
        }

//        while (true) {
//            CotizacionDTO cotizacion = getFactory().manufacturePojo(CotizacionDTO.class);
//            cotizacion.setId(contador);
//            cotizacion.setEmisora(" BIMBO.A " + ++contador);
//            cotizacion.setNombreAccion(" VENTA ");
//            addDay(calendar, cotizacion);
//            cotizacion.setFecha(calendar.getTime());
//            cotizacion.setCostoAdquisicion(Double.valueOf(cotizacion.getPorcentaje()) / 100);
//            cotizacion.setIsRed(cotizacion.getPorcentaje() <= 50);
//            lstCotizacionDTO.add(cotizacion);
//            System.out.println("es rojo " + cotizacion.isIsRed());
//            if (calendar.get(Calendar.YEAR) == 2000) {
//                break;
//            }
//        }

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

    /**
     *
     */
    public void btnImprimirAcuse() {
        try {
            byte[] retorno = reporterService.makeReport(ReportsEnum.REPORTE_PRUEBA, "prueba.pdf", null, lstCotizacionDTO);
            generaDocumento(retorno, MIMETypesEnum.PDF, "Reporte", FileExtensionEnum.PDF);
        } catch (ReporterJasperException ex) {
            msgError("Error al descargar reporte");
            logger.error(ex);
        }
    }

    public List<CotizacionDTO> getLstCotizacionDTO() {
        return lstCotizacionDTO;
    }

    public void setLstCotizacionDTO(List<CotizacionDTO> lstCotizacionDTO) {
        this.lstCotizacionDTO = lstCotizacionDTO;
    }

    public CotizacionDTO getSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(CotizacionDTO seleccionado) {
        this.seleccionado = seleccionado;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

}
