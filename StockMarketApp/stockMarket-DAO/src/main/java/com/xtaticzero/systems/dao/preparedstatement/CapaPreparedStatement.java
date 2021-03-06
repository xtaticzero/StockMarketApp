/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.dao.sql.CapaSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapaPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator {

    private final CapaDTO capa;

    public CapaPreparedStatement(CapaDTO capa) {
        super();
        this.capa = capa;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(CapaSQL.INSERT_CAPA, new String[]{CapaSQL.TABLE_CAPA});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, capa.getEmisora().getEmisora_id());
            fail = false;
        } catch (SQLException ex) {
            logger.error("No se pudo realizar la incercion:");
            logger.error(ex);
            throw ex;
        } finally {
            cerrarPS();
        }
        return ps;
    }

    public CapaDTO getCapa() {
        return capa;
    }

}
