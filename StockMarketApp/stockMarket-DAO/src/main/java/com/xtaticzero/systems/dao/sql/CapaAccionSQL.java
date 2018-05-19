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
public interface CapaAccionSQL {
    
    String TABLE_ACCION = "CAPA_ACCION";
    String INSERT_ACCION = "INSERT ".concat(TABLE_ACCION)
            .concat("(accion_id,capa_id) ")
            .concat("VALUES(?,?)");
    
}
