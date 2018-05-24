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
public class CotizacionDiariaDTO extends BaseModel {

    private static final long serialVersionUID = -980717945340872050L;

    private BigInteger cotizacionId;
    private BigDecimal costoAlDia;
    private Date diaCotizacion;
    private Date fechaTermino;
    private EmisoraDTO emisora;

    public BigInteger getCotizacionId() {
        return cotizacionId;
    }

    public void setCotizacionId(BigInteger cotizacionId) {
        this.cotizacionId = cotizacionId;
    }

    public BigDecimal getCostoAlDia() {
        return costoAlDia;
    }

    public void setCostoAlDia(BigDecimal costoAlDia) {
        this.costoAlDia = costoAlDia;
    }

    public Date getDiaCotizacion() {
        return diaCotizacion != null ? (Date) diaCotizacion.clone() : null;
    }

    public void setDiaCotizacion(Date diaCotizacion) {
        this.diaCotizacion = (diaCotizacion != null ? (Date) diaCotizacion.clone() : null);
    }

    public Date getFechaTermino() {
        return fechaTermino != null ? (Date) fechaTermino.clone() : null;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = (fechaTermino != null ? (Date) fechaTermino.clone() : null);
    }

    public EmisoraDTO getEmisora() {
        return emisora;
    }

    public void setEmisora(EmisoraDTO emisora) {
        this.emisora = emisora;
    }

}
