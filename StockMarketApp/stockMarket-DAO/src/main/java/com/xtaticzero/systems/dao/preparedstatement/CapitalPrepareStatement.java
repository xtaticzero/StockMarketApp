/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.dao.sql.CapitalSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CapitalPrepareStatement extends PreparedStatementBase implements PreparedStatementCreator, CapitalSQL {

    private CapitalDTO capital;

    public CapitalPrepareStatement(CapitalDTO capital) {
        this.capital = capital;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        int numParametro = PARAMETRO_INICIAL;
        try {

            if (capital.getMontoEntrada() != null) {
                ps = connection.prepareStatement(INSERT_ENTRADA_CAPITAL, new String[]{TABLE_CAPITAL});
                //Se asignan id's de la propuesta
                ps.setObject(numParametro++, capital.getMontoEntrada());
                fail = false;
            } else {
                ps = connection.prepareStatement(INSERT_SALIDA_CAPITAL, new String[]{TABLE_CAPITAL});
                //Se asignan id's de la propuesta
                ps.setObject(numParametro++, capital.getMontoSalida());
                fail = false;
            }

        } catch (SQLException ex) {
            logger.error("No se pudo realizar la incercion:");
            logger.error(ex);
            throw ex;
        } finally {
            cerrarPS();
        }
        return ps;
    }

    public CapitalDTO getCapital() {
        return capital;
    }

}
