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

    String ORDER_BY_DATE = " ORDER BY diaMovimiento DESC ";
    String LIMIT_1 = " LIMIT 1";

    String INSERT = "INSERT INTO ".concat(TABLE_INDICE_COTIZACION).concat(" (valorIPC, "
            + "porcentajeCotizacion, "
            + "diaMovimiento )\n"
            + "VALUES(?,?,SYSDATE())");
    String INSERT_BATCH = "INSERT INTO ".concat(TABLE_INDICE_COTIZACION).concat(" (valorIPC, porcentajeCotizacion, diaMovimiento )\n"
            + " VALUES(?,?,?)");

    String UPDATE = "UPDATE ".concat(TABLE_INDICE_COTIZACION).concat("INDICE_COTIZACION SET valorIPC = ?, "
            + "porcentajeCotizacion = ?, "
            + "diaMovimiento = SYSDATE() "
            + "WHERE indiceCotizacion_id = ?");

    String FIND_ALL = "SELECT \n"
            + "indiceCotizacion_id,\n"
            + "valorIPC, \n"
            + "porcentajeCotizacion, \n"
            + "diaMovimiento\n"
            + "FROM INDICE_COTIZACION\n";

    String FIND_LAST_OF_YEAR = FIND_ALL.concat(" WHERE \n"
            + "(diaMovimiento between  DATE_FORMAT((DATE_SUB(SYSDATE(), INTERVAL 1 YEAR)) ,'%Y-01-01') \n"
            + "AND DATE_FORMAT((DATE_SUB(SYSDATE(), INTERVAL 1 YEAR)) ,'%Y-12-31'))").concat(ORDER_BY_DATE).concat(LIMIT_1);
    
    String FIND_ALL_YEARS = FIND_ALL.concat(" WHERE \n"
            + "(diaMovimiento between  DATE_FORMAT((DATE_SUB(SYSDATE(), INTERVAL ? YEAR)) ,'%Y-01-01') \n"
            + "AND SYSDATE()) ").concat(ORDER_BY_DATE);
;
}
