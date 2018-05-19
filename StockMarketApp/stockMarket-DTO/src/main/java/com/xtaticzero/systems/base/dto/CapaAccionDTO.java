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
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapaAccionDTO extends BaseModel {

    private static final long serialVersionUID = 4149323039929250270L;

    private BigInteger caId;
    private AccionDTO accion;
    private CapaDTO capa;

    public BigInteger getCaId() {
        return caId;
    }

    public void setCaId(BigInteger caId) {
        this.caId = caId;
    }

    public AccionDTO getAccion() {
        return accion;
    }

    public void setAccion(AccionDTO accion) {
        this.accion = accion;
    }

    public CapaDTO getCapa() {
        return capa;
    }

    public void setCapa(CapaDTO capa) {
        this.capa = capa;
    }

}
