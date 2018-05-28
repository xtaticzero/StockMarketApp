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
public interface CapitalSQL {

    String TABLE_CAPITAL = "CAPITAL";

    String INSERT_ENTRADA_CAPITAL = "INSERT INTO CAPITAL(montoEntrada,diaMovimiento) VALUES(?,SYSDATE())";

    String INSERT_SALIDA_CAPITAL = "INSERT INTO CAPITAL(montoSalida,diaMovimiento) VALUES(?,SYSDATE())";

    String TOTAL_ENTRADAS = "SELECT SUM(CAPITAL.montoEntrada) total\n"
            + "   FROM CAPITAL;";

    String TOTAL_SALIDAS = "SELECT SUM(CAPITAL.montoSalida) total\n"
            + "   FROM CAPITAL";

    String REPORTE_MOVIMIENTOS = "SELECT \n"
            + "CAP.capital_id,\n"
            + "CAP.montoEntrada,\n"
            + "CAP.montoSalida,\n"
            + "CAP.diaMovimiento\n"
            + "FROM CAPITAL CAP \n"
            + "ORDER BY CAP.diaMovimiento";

}
