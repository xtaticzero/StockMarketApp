/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.enums;

import com.xtaticzero.systems.base.dto.MovimientoDTO;
import java.math.BigInteger;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public enum MovimientosEnum {

    COMPRA(new BigInteger("1"), "COMPRA"),
    VENTA(new BigInteger("2"), "VENTA");

    private final BigInteger id;
    private final String descripcion;

    MovimientosEnum(BigInteger id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public static MovimientoDTO getMovimiento(MovimientosEnum enm) {
        MovimientoDTO mov = new MovimientoDTO();
        mov.setMovimiento_id(enm.getId());
        mov.setDescripcion(enm.getDescripcion());
        return mov;
    }

    public BigInteger getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
