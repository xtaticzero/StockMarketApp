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
import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.base.dto.TransaccionDTO;
import com.xtaticzero.systems.base.enums.MovimientosEnum;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.AccionService;
import com.xtaticzero.systems.business.market.CapaAccionService;
import com.xtaticzero.systems.business.market.CapaService;
import com.xtaticzero.systems.business.market.CapitalService;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.business.market.EmisoraService;
import com.xtaticzero.systems.business.market.TransaccionService;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.context.RequestContext;
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
    @Autowired
    @Qualifier("capitalService")
    private CapitalService capitalService;

    private List<EmisoraDTO> emisoras;
    private BigInteger selectEmisora;
    private AccionDTO accion;
    private CotizacionDiariaDTO cotizacion;
    private List<TransaccionDTO> transacciones;
    private BigDecimal totalCompra = BigDecimal.ZERO;
    private CapitalDTO capital;
    private BigDecimal capitalInit = BigDecimal.ZERO;
    private TransaccionDTO tranSelect;

    private Integer cantidadVenta;
    private BigDecimal totalVenta = BigDecimal.ZERO;

    @PostConstruct
    public void init() throws FrontException {
        try {
            validateUsuarioValido();
            emisoras = emisoraService.obtenerEmisoras();
            accion = new AccionDTO();
            transacciones = transaccionService.obtenerTransacciones();
            capital = capitalService.getCapital();
            capitalInit = capital.getMontoEntrada();
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
        totalCompra = accion.getCostoUnitario().multiply(new BigDecimal(accion.getExistencia() * 1.00174));
    }

    public void agregaCompra() {
        try {
            if (!capital.equals(BigDecimal.ZERO)) {

                if (!capital.getMontoEntrada().equals(capitalInit)) {
                    logger.info("### Cambio el capital " + capitalInit);
                    capital.setMontoEntrada(capitalInit);
                    capital = capitalService.agregarEntrada(capital);
                    capitalInit = capital.getMontoEntrada();
                }

                if (totalCompra.compareTo(capital.getMontoEntrada()) == 1) {
                    msgWarn("La compra supera tu capital");
                } else {
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
                    transaccion.setCantidad(accion.getExistencia());
                    transaccion.setCostoUnitario(accion.getCostoUnitario());
                    transaccion.setTotal(accion.getCostoUnitario().multiply(new BigDecimal(accion.getExistencia() * 1.00174)));
                    transaccion.setUtilidad(BigDecimal.ZERO);
                    transaccion.setPorcentajeMovimiento(BigDecimal.ZERO);

                    transaccionService.guardarTransaccion(transaccion);

                    transacciones = transaccionService.obtenerTransacciones();

                    capital.setMontoSalida(transaccion.getTotal());
                    capitalService.agregarSalida(capital);
                    capital.setMontoEntrada(capitalInit.subtract(transaccion.getTotal()));
                    capital = capitalService.agregarEntrada(capital);
                    capitalInit = capital.getMontoEntrada();

                    msgInfo("Exito!");
                }
            } else {
                msgWarn("Te has quedado sin capital, favor de validar el dato!");
            }

        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    public void calculaCotizacion() {
        try {
            cotizacion = cotizacionService.findCotizacionDiariaByEmisora(tranSelect.getCapaAccion().getCapa().getEmisora().getEmisora_id());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('ventaDialog').show();");
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    public void calculaTotalVenta() {
        try {
            cotizacion = cotizacionService.findCotizacionDiariaByEmisora(tranSelect.getCapaAccion().getCapa().getEmisora().getEmisora_id());
            BigDecimal subTotal = cotizacion.getCostoAlDia().multiply(new BigDecimal(cantidadVenta));
            totalVenta = subTotal.subtract(subTotal.multiply(new BigDecimal(0.00174)));
            BigDecimal utilidad = totalVenta.subtract(tranSelect.getTotal());
            tranSelect.setTotal(totalVenta);
            tranSelect.setUtilidad(utilidad.setScale(6, RoundingMode.HALF_UP));
            tranSelect.setPorcentajeMovimiento(tranSelect.getUtilidad().divide(tranSelect.getTotal(), 6, RoundingMode.HALF_UP));
            totalVenta = tranSelect.getTotal();
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    public void registraVenta() {
        try {
            if (cantidadVenta > tranSelect.getCapaAccion().getAccion().getExistencia()) {
                msgWarn("No puedes vender más de las existentes, verfica el dato");
            } else {
                tranSelect.setMovimiento(MovimientosEnum.getMovimiento(MovimientosEnum.VENTA));
                tranSelect.setCantidad(cantidadVenta);
                tranSelect.setCostoUnitario(cotizacion.getCostoAlDia());

                transaccionService.guardarTransaccion(tranSelect);
                transacciones = transaccionService.obtenerTransacciones();

                capital.setMontoEntrada(capitalInit.add(tranSelect.getTotal()));
                capital = capitalService.agregarEntrada(capital);
                capitalInit = capital.getMontoEntrada();

                totalVenta = BigDecimal.ZERO;
                msgInfo("Venta Realizada!");
            }
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    private int calculaCantidadTotal(EmisoraDTO emisora, MovimientosEnum mov) {
        int sum = 0;
        for (TransaccionDTO tran : transacciones) {
            if (emisora.getEmisora_id().equals(tran.getCapaAccion().getCapa().getEmisora().getEmisora_id())) {
                if (tran.getMovimiento().getMovimiento_id().equals(mov.getId())) {
                    sum += tran.getCantidad();
                }
            }
        }
        return sum;
    }

    private BigDecimal calculaCostoUnitarioTotal(EmisoraDTO emisora, MovimientosEnum mov) {
        BigDecimal sum = BigDecimal.ZERO;
        for (TransaccionDTO tran : transacciones) {
            if (emisora.getEmisora_id().equals(tran.getCapaAccion().getCapa().getEmisora().getEmisora_id())) {
                if (tran.getMovimiento().getMovimiento_id().equals(mov.getId())) {
                    sum = sum.add(tran.getCostoUnitario());
                }
            }
        }
        return sum;
    }

    public int totalEntradas(EmisoraDTO emisora) {
        return calculaCantidadTotal(emisora, MovimientosEnum.COMPRA);
    }

    public int totalSalidas(EmisoraDTO emisora) {
        return calculaCantidadTotal(emisora, MovimientosEnum.VENTA);
    }

    public BigDecimal totalPrecioCompras(EmisoraDTO emisora) {
        return calculaCostoUnitarioTotal(emisora, MovimientosEnum.COMPRA);
    }

    public BigDecimal totalPrecioVentas(EmisoraDTO emisora) {
        return calculaCostoUnitarioTotal(emisora, MovimientosEnum.VENTA);
    }

    public BigDecimal totalTotales(EmisoraDTO emisora) {
        BigDecimal sum = BigDecimal.ZERO;
        for (TransaccionDTO tran : transacciones) {
            if (emisora.getEmisora_id().equals(tran.getCapaAccion().getCapa().getEmisora().getEmisora_id())) {
                sum = sum.add(tran.getTotal());
            }
        }
        return sum.setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal totalUtilidades(EmisoraDTO emisora) {
        BigDecimal sum = BigDecimal.ZERO;
        for (TransaccionDTO tran : transacciones) {
            if (emisora.getEmisora_id().equals(tran.getCapaAccion().getCapa().getEmisora().getEmisora_id())) {
                sum = sum.add(tran.getUtilidad());
            }
        }
        return sum.setScale(4, RoundingMode.HALF_UP);
    }

    public BigDecimal porcentajeTotales(EmisoraDTO emisora) {
        BigDecimal sum = BigDecimal.ZERO;
        for (TransaccionDTO tran : transacciones) {
            if (emisora.getEmisora_id().equals(tran.getCapaAccion().getCapa().getEmisora().getEmisora_id())) {
                sum = sum.add(tran.getPorcentajeMovimiento());
            }
        }
        return sum.setScale(4, RoundingMode.HALF_UP);
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

    public CotizacionDiariaDTO getCotizacion() {
        return cotizacion;
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

    public CapitalDTO getCapital() {
        return capital;
    }

    public void setCapital(CapitalDTO capital) {
        this.capital = capital;
    }

    public BigDecimal getCapitalInit() {
        return capitalInit;
    }

    public void setCapitalInit(BigDecimal capitalInit) {
        this.capitalInit = capitalInit;
    }

    public TransaccionDTO getTranSelect() {
        return tranSelect;
    }

    public void setTranSelect(TransaccionDTO tranSelect) {
        this.tranSelect = tranSelect;
    }

    public Integer getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(Integer cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public BigDecimal getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(BigDecimal totalVenta) {
        this.totalVenta = totalVenta;
    }

}
