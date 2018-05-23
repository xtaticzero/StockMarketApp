/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.AccionDTO;
import com.xtaticzero.systems.dao.sql.AccionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class AccionPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator {

    private final AccionDTO accion;

    public AccionPreparedStatement(AccionDTO accion) {
        super();
        this.accion = accion;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        boolean fail = true;
        PreparedStatement ps = null;
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(AccionSQL.INSERT_ACCION, new String[]{AccionSQL.TABLE_ACCION});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, accion.getCostoUnitario());
            ps.setObject(numParametro++, accion.getExistencia());
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

    public AccionDTO getAccion() {
        return accion;
    }

}
