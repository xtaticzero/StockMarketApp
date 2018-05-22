/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CapaSQL extends BaseSQL {

    String TABLE_CAPA = "CAPA";
    String INSERT_CAPA = "INSERT ".concat(TABLE_CAPA).concat("(emisora_id,activo) VALUES(?,1)");

    String EXISTE_CAPA_ACTIVA = "select capa_id\n"
            + "from capa capa\n"
            + "join emisora emi\n"
            + "    on emi.emisora_id = capa.emisora_id\n"
            + "    and emi.emisora_id = ?";

    String EXISTE_CAPA = "select capa_id\n"
            + "from capa capa\n"
            + "join emisora emi\n"
            + "    on emi.emisora_id = capa.emisora_id\n"
            + "    and emi.emisora_id = ?";

    String FIND_CAPA_BY_ID = "select capa_id,accion_id,emisora_id,activo from capa where capa_id=?";

    String ACCION_BY_ID = "AC.accion_id = ?";
    
    String EMISORA_BY_ID = "EMI.emisora_id = ?";

    String HEDER_SELECT_CAPA = "SELECT \n"
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
