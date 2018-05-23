/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigInteger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CapaDTO extends BaseModel {

    private static final long serialVersionUID = 4149393039959250570L;

    private BigInteger capa_id;
    private EmisoraDTO emisora;
    private Boolean activo;

    public BigInteger getCapa_id() {
        return capa_id;
    }

    public void setCapa_id(BigInteger capa_id) {
        this.capa_id = capa_id;
    }

    public EmisoraDTO getEmisora() {
        return emisora;
    }

    public void setEmisora(EmisoraDTO emisora) {
        this.emisora = emisora;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

}
