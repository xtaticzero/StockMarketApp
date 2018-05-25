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
public interface CotizacionDiariaSQL extends BaseSQL{

    String TABLE_COTIZACION_DIARIA = "COTIZACION_DIARIA";

    String INSERT_COTIZACION = "INSERT ".concat(TABLE_COTIZACION_DIARIA).concat("(costo_al_dia,diaCotizacion,emisora_id) \n"
            + "VALUES(?,SYSDATE(),?)");

    String UPDATE_COTIZACION = "UPDATE ".concat(TABLE_COTIZACION_DIARIA).concat(" SET costo_al_dia = ?, diaCotizacion = SYSDATE() \n"
            + "WHERE emisora_id = ? AND cotizacion_id = ?");

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
    
    String FIND_COTIZACION_BY_EMISORA = FIND_COTIZACION_ALL.concat(WHERE).concat(PARAMETRO_EMISORA_ID).concat(ORDER_BY_EMISORA_ID);
            

}
