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
public interface EmisorasSQL {

    String TABLE_EMISORA = "EMISORA";
    String INSERT_EMISORA = "INSERT ".concat(TABLE_EMISORA).concat("(nombre,fechaEntrada) VALUES(?,SYSDATE())");

    String DELETE_EMISORA = "UPDATE EMISORA \n"
            + "SET fechaBaja = SYSDATE() \n"
            + "WHERE emisora_id = ?";

    String UPDATE_EMISORA = "UPDATE EMISORA\n"
            + "SET nombre = ?,\n"
            + "fechaBaja = ? \n"
            + "WHERE emisora_id = ?";

    String SELECT_ALL_EMISORAS = "SELECT \n"
            + "EMI.emisora_id,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "FROM EMISORA EMI\n"
            + "ORDER BY EMI.emisora_id";

    String FIND_EMISORA_BY_NAME = "SELECT \n"
            + "EMI.emisora_id,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "FROM EMISORA EMI\n"
            + "WHERE EMI.nombre = ?\n"
            + "ORDER BY EMI.emisora_id";

    String FIND_EMISORA_BY_ID = "SELECT \n"
            + "EMI.emisora_id,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "FROM EMISORA EMI\n"
            + "WHERE EMI.emisora_id = ?";
}
