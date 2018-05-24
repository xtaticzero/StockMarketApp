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

    String SELECT_ALL_CAPA_ACCION = "SELECT CA.ca_id,\n"
            + "CP.capa_id,CP.activo,\n"
            + "AC.accion_id,AC.fechaCompra,AC.fechaVenta,AC.costoUnitario,AC.existencia,\n"
            + "EMI.emisora_id,EMI.nombre,EMI.fechaEntrada,EMI.fechaBaja\n"
            + "from CAPA_ACCION CA\n"
            + "JOIN CAPA CP ON CP.capa_id = CA.capa_id\n"
            + "JOIN ACCION AC ON AC.accion_id = CA.accion_id\n"
            + "JOIN EMISORA EMI on EMI.emisora_id = CP.emisora_id";

}
