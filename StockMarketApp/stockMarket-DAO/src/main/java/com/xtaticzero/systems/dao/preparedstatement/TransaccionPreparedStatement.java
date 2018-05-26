/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.TransaccionDTO;
import static com.xtaticzero.systems.dao.preparedstatement.PreparedStatementBase.PARAMETRO_INICIAL;
import com.xtaticzero.systems.dao.sql.TransaccionSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class TransaccionPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator{

    private final TransaccionDTO transaccion;

    public TransaccionPreparedStatement(TransaccionDTO transaccion) {
        super();
        this.transaccion = transaccion;
    }
    
    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
     int numParametro = PARAMETRO_INICIAL;
        try {
                ps = connection.prepareStatement(TransaccionSQL.INSERT_TRANSACCION, new String[]{TransaccionSQL.TABLE_TRANSACCION});
            //Se asignan id's de la propuesta
            
            ps.setObject(numParametro++, transaccion.getCapaAccion().getCaId());
            ps.setObject(numParametro++, transaccion.getMovimiento().getMovimiento_id());
            ps.setObject(numParametro++, transaccion.getImporte());
            ps.setObject(numParametro++, transaccion.getCosto());
            ps.setObject(numParametro++, transaccion.getUtilidad());
            ps.setObject(numParametro++, transaccion.getPorcentajeVenta());
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

}
