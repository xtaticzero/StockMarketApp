/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.IPCDto;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class IpcMapper extends BaseAbstractMapper implements RowMapper<IPCDto> {

    @Override
    public IPCDto mapRow(ResultSet rs, int i) throws SQLException {
        IPCDto ipc = new IPCDto();

        ipc.setIndiceCotizacion_id(new BigInteger(rs.getString(IPC_ID)));
        ipc.setValorIPC(new BigDecimal(rs.getString(IPC_VALOR)));
        if (rs.getString(IPC_PORCENTAJE) != null) {
            ipc.setPorcentajeCotizacion(new BigDecimal(rs.getString(IPC_PORCENTAJE)));
        }
        ipc.setDiaMovimiento((rs.getTimestamp(IPC_DIA_MOVIMIENTO)));

        return ipc;
    }

}
