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

    String SELECT_CAPA = "SELECT \n"
            + "CP.capa_id,\n"
            + "AC.accion_id,\n"
            + "EMI.emisora_id,\n"
            + "CP.activo,\n"
            + "AC.fechaCompra,\n"
            + "AC.fechaVenta,\n"
            + "AC.costoUnitario,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "from CAPA CP\n"
            + "INNER JOIN ACCION AC ON CP.accion_id = AC.accion_id\n"
            + "INNER JOIN EMISORA EMI ON CP.emisora_id = EMI.emisora_id\n"
            + "WHERE 1=1";

}
