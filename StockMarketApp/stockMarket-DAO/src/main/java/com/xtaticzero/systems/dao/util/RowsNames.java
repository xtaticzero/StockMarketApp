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
    String TRANSACCION_CANTIDAD = "TRA.cantidad";
    String TRANSACCION_COSTO = "TRA.costoUnitario";
    String TRANSACCION_TOTAL = "TRA.total";
    String TRANSACCION_UTILIDAD = "TRA.utilidad";
    String TRANSACCION_PORCENTAJE = "TRA.porcentajeMovimiento";

    String COTIZACION_ID = "COT.cotizacion_id";
    String COTIZACION_AL_DIA = "COT.costo_al_dia";
    String COTIZACION_FECHA_COTIZACION = "COT.diaCotizacion";
    String COTIZACION_FECHA_BAJA = "COT.fecha_termino";
    
    String COTIZACION_HISTORY_ID = "COT_H.cotizacion_history_id";
    String COTIZACION_HISTORY_COSTO = "COT_H.costo_al_dia";
    String COTIZACION_HISTORY_FECHA = "COT_H.diaCotizacion";

    String COMISION_ID = "COM.comision_id";
    String COMISION_PORCENTAJE = "COM.porcentaje";
    String COMISION_DESCRIPCION = "COM.descripcion";

    String CAPITAL_ID = "CAP.capital_id";
    String CAPITAL_MONTO_ENTRADA = "CAP.montoEntrada";
    String CAPITAL_MONTO_SALIDA = "CAP.montoSalida";
    String CAPITAL_DIA_MOVIMIENTO = "CAP.diaMovimiento";
    
    String IPC_ID = "indiceCotizacion_id";
    String IPC_VALOR = "valorIPC";
    String IPC_PORCENTAJE = "porcentajeCotizacion";
    String IPC_DIA_MOVIMIENTO = "diaMovimiento";

    String TOTAL = "total";
    
    String COTIZACION_HISTORY_YEAR = "YEAR"; 
}
