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

    String FIND_CAPA_BY_ID = "SELECT  CP.capa_id, CP.activo,\n"
            + "EMI.emisora_id, EMI.nombre, EMI.fechaEntrada, EMI.fechaBaja\n"
            + "from CAPA CP\n"
            + "JOIN EMISORA EMI \n"
            + "    ON CP.emisora_id = EMI.emisora_id\n"
            + "where CP.capa_id=?";

    String ACCION_BY_ID = "AC.accion_id = ?";

    String EMISORA_BY_ID = "EMI.emisora_id = ?";

    String HEDER_SELECT_CAPA = "SELECT \n"
            + "CP.capa_id,\n"
            + "EMI.emisora_id,\n"
            + "CP.activo,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "from CAPA CP\n"
            + "INNER JOIN EMISORA EMI ON CP.emisora_id = EMI.emisora_id\n"
            + "WHERE 1=1";
}
