/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class TransaccionDTO extends BaseModel {

    private static final long serialVersionUID = 833068187881704761L;

    private BigInteger transaccion_id;
    private CapaAccionDTO capaAccion;
    private MovimientoDTO movimiento;
    private Date fechaTransaccion;
    private BigDecimal importe;
    private BigDecimal costo;
    private BigDecimal utilidad;
    private BigDecimal porcentajeVenta;

    public BigInteger getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(BigInteger transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public CapaAccionDTO getCapaAccion() {
        return capaAccion;
    }

    public void setCapaAccion(CapaAccionDTO capaAccion) {
        this.capaAccion = capaAccion;
    }

    public MovimientoDTO getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }

    public Date getFechaTransaccion() {
        return (fechaTransaccion != null) ? (Date) fechaTransaccion : null;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = (fechaTransaccion != null) ? (Date) fechaTransaccion : null;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getCosto() {
        return costo;
    }

    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public BigDecimal getPorcentajeVenta() {
        return porcentajeVenta;
    }

    public void setPorcentajeVenta(BigDecimal porcentajeVenta) {
        this.porcentajeVenta = porcentajeVenta;
    }

}
