/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.util;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface RowsNames {

    String CAPA_ID = "CP.capa_id";
    String CAPA_ACTIVO = "CP.activo";

    String EMISORA_ID = "EMI.emisora_id";
    String EMISORA_NOMBRE = "EMI.nombre";
    String EMISORA_FECHA_ENTRADA = "EMI.fechaEntrada";
    String EMISORA_FECHA_BAJA = "EMI.fechaBaja";

    String ACCION_ID = "AC.accion_id";
    String ACCION_FECHA_COMPRA = "AC.fechaCompra";
    String ACCION_FECHA_VENTA = "AC.fechaVenta";
    String ACCION_COSTO_UNITARIO = "AC.costoUnitario";
    String ACCION_EXISTENCIA = "AC.existencia";

    String CAPA_ACCION_ID = "CA.ca_id";

    String MOVIMIENTO_ID = "MOV.movimiento_id";
    String MOVIMIENTO_DESC = "MOV.descripcion";

    String TRANSACCION_ID = "TRA.transaccion_id";
    String TRANSACCION_FECHA = "TRA.fechaTransaccion";
    String TRANSACCION_IMPORTE = "TRA.importe";
    String TRANSACCION_COSTO = "TRA.costo";
    String TRANSACCION_UTILIDAD = "TRA.utilidad";
    String TRANSACCION_PORCENTAJE = "TRA.porcentaje_Movimiento";

    String COTIZACION_ID = "COT.cotizacion_id";
    String COTIZACION_AL_DIA = "COT.costo_al_dia";
    String COTIZACION_FECHA_COTIZACION = "COT.diaCotizacion";
    String COTIZACION_FECHA_BAJA = "COT.fecha_termino";
}
