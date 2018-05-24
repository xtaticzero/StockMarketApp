/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.dao.sql.CotizacionDiariaSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionDiariaPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator,CotizacionDiariaSQL {

    private final CotizacionDiariaDTO cotizacionDiaria;

    public CotizacionDiariaPreparedStatement(CotizacionDiariaDTO CotizacionDiaria) {
        this.cotizacionDiaria = CotizacionDiaria;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        boolean fail = true;
        PreparedStatement ps = null;
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(CotizacionDiariaSQL.INSERT_COTIZACION, new String[]{CotizacionDiariaSQL.TABLE_COTIZACION_DIARIA});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, cotizacionDiaria.getCostoAlDia());
            ps.setObject(numParametro++, cotizacionDiaria.getEmisora().getEmisora_id());
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

    public CotizacionDiariaDTO getCotizacionDiaria() {
        return cotizacionDiaria;
    }

}
