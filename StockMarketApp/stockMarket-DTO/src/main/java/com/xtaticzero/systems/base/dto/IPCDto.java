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
public class IPCDto extends BaseModel {

    private static final long serialVersionUID = -4659942635399586998L;

    private BigInteger indiceCotizacion_id;
    private BigDecimal valorIPC;
    private BigDecimal porcentajeCotizacion;
    private Date diaMovimiento;

    public BigInteger getIndiceCotizacion_id() {
        return indiceCotizacion_id;
    }

    public void setIndiceCotizacion_id(BigInteger indiceCotizacion_id) {
        this.indiceCotizacion_id = indiceCotizacion_id;
    }

    public BigDecimal getValorIPC() {
        return valorIPC;
    }

    public void setValorIPC(BigDecimal valorIPC) {
        this.valorIPC = valorIPC;
    }

    public BigDecimal getPorcentajeCotizacion() {
        return porcentajeCotizacion;
    }

    public void setPorcentajeCotizacion(BigDecimal porcentajeCotizacion) {
        this.porcentajeCotizacion = porcentajeCotizacion;
    }

    public Date getDiaMovimiento() {        
        return diaMovimiento!=null?(Date)diaMovimiento.clone():null;
    }

    public void setDiaMovimiento(Date diaMovimiento) {
        this.diaMovimiento = diaMovimiento!=null?(Date)diaMovimiento.clone():null;
    }

}
