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
public interface CotizacionDiariaSQL extends BaseSQL {

    String TABLE_COTIZACION_DIARIA = "COTIZACION_DIARIA";

    String INSERT_COTIZACION = "INSERT ".concat(TABLE_COTIZACION_DIARIA).concat("(costo_al_dia,diaCotizacion,emisora_id) \n"
            + "VALUES(?,SYSDATE(),?)");

    String UPDATE_COTIZACION = "UPDATE ".concat(TABLE_COTIZACION_DIARIA).concat(" SET costo_al_dia = ?, diaCotizacion = SYSDATE() \n"
            + "WHERE emisora_id = ? AND fecha_termino is null");

    String DELETE_COTIZACION = "UPDATE ".concat(TABLE_COTIZACION_DIARIA).concat(" SET fecha_termino = SYSDATE() "
            + "WHERE emisora_id = ? AND cotizacion_id = ?");

    String PARAMETRO_EMISORA_ID = " EMI.emisora_id = ?";
    String ORDER_BY_EMISORA_ID = " ORDER by COT.cotizacion_id\n";

    String FIND_COTIZACION_ALL = "SELECT \n"
            + "COT.cotizacion_id,\n"
            + "COT.costo_al_dia,\n"
            + "COT.diaCotizacion,\n"
            + "COT.fecha_termino,\n"
            + "EMI.emisora_id,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "FROM COTIZACION_DIARIA COT\n"
            + "INNER JOIN EMISORA EMI ON EMI.emisora_id = COT.emisora_id\n";

    String FIND_COTIZACION_BY_EMISORA = FIND_COTIZACION_ALL.concat(WHERE)
            .concat(PARAMETRO_EMISORA_ID).concat(ORDER_BY_EMISORA_ID);
    
    String DATE_FILTER = "{DATE_FILTER}";

    String FIND_COTIZACION_HISTORY_BY_EMISORA = "SELECT \n"
            + "COT_H.cotizacion_history_id,\n"
            + "COT.cotizacion_id,\n"
            + "COT_H.costo_al_dia,\n"
            + "COT.costo_al_dia,\n"
            + "COT_H.diaCotizacion,\n"
            + "COT.diaCotizacion,\n"
            + "COT.fecha_termino,\n"
            + "EMI.emisora_id,\n"
            + "EMI.nombre,\n"
            + "EMI.fechaEntrada,\n"
            + "EMI.fechaBaja\n"
            + "FROM COTIZACION_DIARIA_HISTORY COT_H\n"
            + "INNER JOIN COTIZACION_DIARIA COT ON COT.cotizacion_id = COT_H.cotizacion_id\n"
            + "INNER JOIN EMISORA EMI ON COT_H.emisora_id = EMI.emisora_id\n"
            + "WHERE 1=1\n"
            + "AND \n"
            + "EMI.emisora_id = ?\n"
            + "AND\n"
            + "(COT_H.diaCotizacion between  DATE_FORMAT({DATE_FILTER} ,'%Y-%m-01') AND SYSDATE() )\n"
            + "ORDER BY COT_H.diaCotizacion DESC"; 
    

    String SQL_INDICE_COTIZACION_HEADER = "SELECT\n"
            + "EMI.nombre,\n"
            + "COT.costo_al_dia,\n"
            + "COT_H.costo_al_dia\n"
            + "FROM COTIZACION_DIARIA COT\n"
            + "INNER JOIN EMISORA EMI ON COT.emisora_id = EMI.emisora_id\n"
            + "LEFT JOIN COTIZACION_DIARIA_HISTORY COT_H ON COT.emisora_id = COT_H.emisora_id\n"
            + "WHERE COT.emisora_id = ?";
    
    String SQL_INDICE_COTIZACION_FOOTER_DAY = "AND\n"
            + "DATE_FORMAT(?, '%Y-%m-%d 00:00:00') \n"
            + "AND DATE_FORMAT(?, '%Y-%m-%d 23:59:59') ORDER BY COT_H.diaCotizacion ASC LIMIT 1";
    
    String SQL_INDICE_COTIZACION_FOOTER_YEAR = "AND\n"
            + "DATE_FORMAT(?, '%Y-01-01 00:00:00') \n"
            + "AND DATE_FORMAT(?, '%Y-%m-%d 23:59:59') ORDER BY COT_H.diaCotizacion ASC LIMIT 1";

    String INDICE_COTIZACION_X_DIA = SQL_INDICE_COTIZACION_HEADER.concat(AND).concat(SQL_INDICE_COTIZACION_FOOTER_DAY);

    String INDICE_COTIZACION_X_YEAR = SQL_INDICE_COTIZACION_HEADER.concat(AND).concat(SQL_INDICE_COTIZACION_FOOTER_YEAR);

}
