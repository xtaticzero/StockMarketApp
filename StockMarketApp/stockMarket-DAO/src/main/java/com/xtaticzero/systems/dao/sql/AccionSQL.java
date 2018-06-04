/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.sql;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public interface AccionSQL {

    String TABLE_ACCION = "ACCION";
    String INSERT_ACCION = "INSERT ".concat(TABLE_ACCION)
            .concat("(fechaCompra,fechaVenta,costoUnitario,existencia) ")
            .concat("VALUES(SYSDATE(),null,?,?)");
    String UPDATE_ACCION = "UPDATE ".concat(TABLE_ACCION).concat(" SET existencia = ?, fechaVenta = SYSDATE() \n"
            + "WHERE accion_id = ?");

}
