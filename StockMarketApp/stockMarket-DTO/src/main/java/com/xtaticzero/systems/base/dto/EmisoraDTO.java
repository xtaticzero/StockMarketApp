/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class EmisoraDTO extends BaseModel {

    private static final long serialVersionUID = -3519215746010041020L;

    private BigInteger emisora_id;
    private String nombre;
    private Date fechaEntrada;
    private Date fechaBaja;

    public BigInteger getEmisora_id() {
        return emisora_id;
    }

    public void setEmisora_id(BigInteger emisora_id) {
        this.emisora_id = emisora_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaEntrada() {
        return (fechaEntrada != null) ? (Date) (fechaEntrada.clone()) : null;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = (fechaEntrada != null) ? (Date) fechaEntrada.clone() : null;
    }

    public Date getFechaBaja() {
        return fechaBaja = (fechaBaja != null) ? (Date) (fechaBaja.clone()) : null;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = (fechaBaja != null) ? (Date) (fechaBaja.clone()) : null;
    }

}
