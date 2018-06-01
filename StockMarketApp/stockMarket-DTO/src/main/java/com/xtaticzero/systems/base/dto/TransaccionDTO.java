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
    private Integer cantidad;
    private BigDecimal costoUnitario;
    private BigDecimal total;    
    private BigDecimal utilidad;
    private BigDecimal porcentajeMovimiento;

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

    public BigDecimal getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(BigDecimal utilidad) {
        this.utilidad = utilidad;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(BigDecimal costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPorcentajeMovimiento() {
        return porcentajeMovimiento;
    }

    public void setPorcentajeMovimiento(BigDecimal porcentajeMovimiento) {
        this.porcentajeMovimiento = porcentajeMovimiento;
    }
    
}
