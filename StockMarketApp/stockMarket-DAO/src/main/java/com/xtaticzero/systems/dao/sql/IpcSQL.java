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
public interface IpcSQL {

    String TABLE_INDICE_COTIZACION = "INDICE_COTIZACION";

    String INSERT = "INSERT INTO ".concat(TABLE_INDICE_COTIZACION).concat(" (volorIPC, "
            + "porcentajeCotizacion, "
            + "diaMovimiento )\n"
            + "VALUES(?,?,SYSDATE())");

    String UPDATE = "UPDATE ".concat(TABLE_INDICE_COTIZACION).concat("INDICE_COTIZACION SET valorIPC = ?, "
            + "porcentajeCotizacion = ?, "
            + "diaMovimiento = SYSDATE() "
            + "WHERE indiceCotizacion_id = ?");
    
    String FIND_ALL = "";
}
