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
public interface TransaccionSQL {

    String TABLE_TRANSACCION = "TRANSACCION";
    String INSERT_TRANSACCION = "INSERT ".concat(TABLE_TRANSACCION)
            .concat("(ca_id, movimiento_id, fechaTransaccion, cantidad, costoUnitario, total,utilidad, porcentajeMovimiento) ")
            .concat("VALUES(?,?,SYSDATE(),?,?,?,?,?)");

    String SELECT_ALL_TRANSACCION = "select TRA.transaccion_id, TRA.fechaTransaccion,TRA.cantidad, TRA.costoUnitario, TRA.total, TRA.utilidad, TRA.porcentajeMovimiento,\n"
            + "MOV.movimiento_id,MOV.descripcion,\n"
            + "CA.ca_id,\n"
            + "CP.capa_id,CP.activo,\n"
            + "AC.accion_id,AC.fechaCompra,AC.fechaVenta,AC.costoUnitario,AC.existencia,\n"
            + "EMI.emisora_id,EMI.nombre,EMI.fechaEntrada,EMI.fechaBaja\n"
            + "from TRANSACCION TRA\n"
            + "JOIN MOVIMIENTO MOV ON MOV.movimiento_id = TRA.movimiento_id\n"
            + "JOIN CAPA_ACCION CA ON CA.ca_id= TRA.ca_id\n"
            + "JOIN CAPA CP ON CP.capa_id = CA.capa_id\n"
            + "JOIN ACCION AC ON AC.accion_id = CA.accion_id\n"
            + "JOIN EMISORA EMI on EMI.emisora_id = CP.emisora_id";

}
