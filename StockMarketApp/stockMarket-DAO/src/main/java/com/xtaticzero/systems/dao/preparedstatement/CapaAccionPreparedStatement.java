/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import com.xtaticzero.systems.dao.sql.CapaAccionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapaAccionPreparedStatement implements PreparedStatementCreator {
    
    protected final Logger logger = Logger.getLogger(getClass());

    private static final int PARAMETRO_INICIAL = 1;
    private CapaAccionDTO accion;
    
    public CapaAccionPreparedStatement(CapaAccionDTO accion) {
        super();
        this.accion = accion;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        boolean fail = true;
        PreparedStatement ps = null;
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(CapaAccionSQL.INSERT_ACCION, new String[]{CapaAccionSQL.TABLE_ACCION});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++,accion.getAccion().getAccion_id());
            ps.setObject(numParametro++,accion.getCapa().getCapa_id());
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

}
