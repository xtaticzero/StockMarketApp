/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class InventarioDTO extends BaseModel {

    private static final long serialVersionUID = 6617110428618247037L;

    private BigInteger inventario_id;
    private AccionDTO accion;
    private BigDecimal existencia;

    public BigInteger getInventario_id() {
        return inventario_id;
    }

    public void setInventario_id(BigInteger inventario_id) {
        this.inventario_id = inventario_id;
    }

    public AccionDTO getAccion() {
        return accion;
    }

    public void setAccion(AccionDTO accion) {
        this.accion = accion;
    }

    public BigDecimal getExistencia() {
        return existencia;
    }

    public void setExistencia(BigDecimal existencia) {
        this.existencia = existencia;
    }

}
