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
import java.util.Objects;

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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.valorIPC);
        hash = 17 * hash + Objects.hashCode(this.diaMovimiento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IPCDto other = (IPCDto) obj;
        if (!Objects.equals(this.valorIPC, other.valorIPC)) {
            return false;
        }
        if (!Objects.equals(this.diaMovimiento, other.diaMovimiento)) {
            return false;
        }
        return true;
    }

    
}
