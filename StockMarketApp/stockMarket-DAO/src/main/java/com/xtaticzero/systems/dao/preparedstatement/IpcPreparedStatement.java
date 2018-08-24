/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.dao.sql.IpcSQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class IpcPreparedStatement extends PreparedStatementBase implements PreparedStatementCreator {

    private final IPCDto ipc;

    public IpcPreparedStatement(IPCDto ipc) {
        super();
        this.ipc = ipc;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
        int numParametro = PARAMETRO_INICIAL;
        try {
            ps = connection.prepareStatement(IpcSQL.INSERT, new String[]{IpcSQL.TABLE_INDICE_COTIZACION});
            //Se asignan id's de la propuesta
            ps.setObject(numParametro++, ipc.getValorIPC());
            ps.setObject(numParametro++, ipc.getPorcentajeCotizacion());
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

    public IPCDto getIpc() {
        return ipc;
    }

}
