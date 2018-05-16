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
public class MovimientoDTO extends BaseModel {

    private static final long serialVersionUID = -5894823857502065715L;

    private BigInteger movimiento_id;
    private String descripcion;

    public BigInteger getMovimiento_id() {
        return movimiento_id;
    }

    public void setMovimiento_id(BigInteger movimiento_id) {
        this.movimiento_id = movimiento_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
