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
    private CapaDTO capa;
    private MovimientoDTO movimiento;
    private Date fechaTransaccion;
    private BigDecimal importe;
    private BigDecimal costo;
    private BigDecimal unidad;
    private BigDecimal costoMovimiento;

    public BigInteger getTransaccion_id() {
        return transaccion_id;
    }

    public void setTransaccion_id(BigInteger transaccion_id) {
        this.transaccion_id = transaccion_id;
    }

    public CapaDTO getCapa() {
        return capa;
    }

    public void setCapa(CapaDTO capa) {
        this.capa = capa;
    }

    public MovimientoDTO getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(MovimientoDTO movimiento) {
        this.movimiento = movimiento;
    }

    public Date getFechaTransaccion() {
        return (fechaTransaccion!=null)?(Date)fechaTransaccion:null;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = (fechaTransaccion!=null)?(Date)fechaTransaccion:null;
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

    public BigDecimal getUnidad() {
        return unidad;
    }

    public void setUnidad(BigDecimal unidad) {
        this.unidad = unidad;
    }

    public BigDecimal getCostoMovimiento() {
        return costoMovimiento;
    }

    public void setCostoMovimiento(BigDecimal costoMovimiento) {
        this.costoMovimiento = costoMovimiento;
    }

}
