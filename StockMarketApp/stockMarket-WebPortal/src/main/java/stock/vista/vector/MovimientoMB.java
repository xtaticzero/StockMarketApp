/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.FrontException;
import com.xtaticzero.systems.base.dto.AccionDTO;
import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.base.dto.TransaccionDTO;
import com.xtaticzero.systems.base.enums.MovimientosEnum;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.AccionService;
import com.xtaticzero.systems.business.market.CapaAccionService;
import com.xtaticzero.systems.business.market.CapaService;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.business.market.EmisoraService;
import com.xtaticzero.systems.business.market.TransaccionService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.vista.VistaAbstractMB;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Controller("movimientoMB")
@Scope(value = "view")
public class MovimientoMB extends VistaAbstractMB {

    @Autowired
    @Qualifier("emisoraService")
    private EmisoraService emisoraService;
    @Autowired
    @Qualifier("capaService")
    private CapaService capaService;
    @Autowired
    @Qualifier("accionService")
    private AccionService accionService;
    @Autowired
    @Qualifier("capaAccionService")
    private CapaAccionService capaAccionService;
    @Autowired
    @Qualifier("transaccionService")
    private TransaccionService transaccionService;
    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;

    private List<EmisoraDTO> emisoras;
    private BigInteger selectEmisora;
    private AccionDTO accion;
    private CotizacionDiariaDTO cotizacion;
    private List<TransaccionDTO> transacciones;
    private BigDecimal totalCompra = BigDecimal.ZERO;

    @PostConstruct
    public void init() throws FrontException {
        try {
            validateUsuarioValido();
            emisoras = emisoraService.obtenerEmisoras();
            accion = new AccionDTO();
            transacciones = transaccionService.obtenerTransacciones();
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    public void changeEmisora() {
        try {
            cotizacion = cotizacionService.findCotizacionDiariaByEmisora(selectEmisora);
            accion.setCostoUnitario(cotizacion.getCostoAlDia());
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    public void calculaTotalCompra() {
        System.out.println("### Cost Unit: " + accion.getCostoUnitario());
        System.out.println("### Existencia: " + accion.getExistencia());
        totalCompra = accion.getCostoUnitario().multiply(new BigDecimal(accion.getExistencia()));
    }

    public void agregaCompra() {
        try {
            if (!cotizacion.getCostoAlDia().equals(accion.getCostoUnitario())) {
                logger.info("### Cambio el costo al día " + accion.getCostoUnitario());
                CotizacionVectorBO cotizacioBO = CotizacionVectorBO.getBOValido(getUsuario());
                cotizacion.setCostoAlDia(accion.getCostoUnitario());
                cotizacioBO.setCotizacionSeleccionada(cotizacion);

                cotizacionService.actualizarCotizacion(cotizacioBO);
            }

            logger.info("### Asignar Capa " + selectEmisora);
            CapaDTO capa = capaService.asignaCapa(selectEmisora);
            accion = accionService.guardarAccion(accion);

            CapaAccionDTO relacion = new CapaAccionDTO();
            relacion.setAccion(accion);
            relacion.setCapa(capa);

            CapaAccionDTO capaAccion = capaAccionService.guardarAccion(relacion);
            cotizacion = cotizacionService.findCotizacionDiariaByEmisora(selectEmisora);

            TransaccionDTO transaccion = new TransaccionDTO();

            transaccion.setCapaAccion(capaAccion);
            transaccion.setMovimiento(MovimientosEnum.getMovimiento(MovimientosEnum.COMPRA));
            transaccion.setImporte(cotizacion.getCostoAlDia().multiply(new BigDecimal(accion.getExistencia())));
            transaccion.setCosto(accion.getCostoUnitario().multiply(new BigDecimal(accion.getExistencia())));
            transaccion.setUtilidad(transaccion.getImporte().subtract(transaccion.getCosto()));
            transaccion.setPorcentajeVenta((transaccion.getUtilidad().divide(transaccion.getImporte())).multiply(new BigDecimal(100)));

            transaccionService.guardarTransaccion(transaccion);

            transacciones = transaccionService.obtenerTransacciones();
            msgInfo("Exito!");

        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    /* getters and setters */
    public List<EmisoraDTO> getEmisoras() {
        return emisoras;
    }

    public BigInteger getSelectEmisora() {
        return selectEmisora;
    }

    public void setSelectEmisora(BigInteger selectEmisora) {
        this.selectEmisora = selectEmisora;
    }

    public AccionDTO getAccion() {
        return accion;
    }

    public void setAccion(AccionDTO accion) {
        this.accion = accion;
    }

    public List<TransaccionDTO> getTransacciones() {
        return transacciones;
    }

    public void setTransacciones(List<TransaccionDTO> transacciones) {
        this.transacciones = transacciones;
    }

    public BigDecimal getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(BigDecimal totalCompra) {
        this.totalCompra = totalCompra;
    }

}
