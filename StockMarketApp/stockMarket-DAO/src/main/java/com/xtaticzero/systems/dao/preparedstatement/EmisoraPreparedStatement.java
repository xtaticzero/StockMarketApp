/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.dao.sql.EmisorasSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class EmisoraPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator {

    private final EmisoraDTO emisora;

    public EmisoraPreparedStatement(EmisoraDTO emisora) {
        super();
        this.emisora = emisora;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        boolean fail = true;
        PreparedStatement ps = null;
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(EmisorasSQL.INSERT_EMISORA, new String[]{EmisorasSQL.TABLE_EMISORA});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, emisora.getNombre());
            fail = false;
        } catch (SQLException ex) {
            logger.error("No se pudo realizar la incercion:");
            logger.error(ex);
            throw ex;
        } finally {
            if (fail) {
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException warn) {
                    logger.error(warn);
                    throw warn;
                }
            }
        }
        return ps;
    }

    public EmisoraDTO getEmisora() {
        return emisora;
    }

}
