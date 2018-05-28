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
public class CapitalDTO extends BaseModel {

    private static final long serialVersionUID = -3525507961110227828L;

    private BigInteger capitalId;
    private BigDecimal montoEntrada;
    private BigDecimal montoSalida;
    private Date diaMovimiento;

    public BigInteger getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(BigInteger capitalId) {
        this.capitalId = capitalId;
    }

    public BigDecimal getMontoEntrada() {
        return montoEntrada;
    }

    public void setMontoEntrada(BigDecimal montoEntrada) {
        this.montoEntrada = montoEntrada;
    }

    public BigDecimal getMontoSalida() {
        return montoSalida;
    }

    public void setMontoSalida(BigDecimal montoSalida) {
        this.montoSalida = montoSalida;
    }

    public Date getDiaMovimiento() {
        return diaMovimiento;
    }

    public void setDiaMovimiento(Date diaMovimiento) {
        this.diaMovimiento = diaMovimiento;
    }

    
}
