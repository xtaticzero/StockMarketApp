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
public interface CapaSQL {

    String TABLE_CAPA = "CAPA";
    String INSERT_CAPA = "INSERT ".concat(TABLE_CAPA).concat("(accion_id,emisora_id,activo) VALUES(?,?,1)");

    String EXISTE_CAPA_ACTIVA = "select capa_id\n"
            + "from capa capa\n"
            + "join emisora emi\n"
            + "    on emi.emisora_id = capa.emisora_id\n"
            + "    and emi.emisora_id = ?";

    String FIND_CAPA_BY_ID = "select capa_id,accion_id,emisora_id,activo from capa where capa_id=?";
}
