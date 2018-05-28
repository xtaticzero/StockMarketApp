/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.dao.util.RowsNames;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CapitalMapper extends BaseAbstractMapper implements RowMapper<CapitalDTO>, RowsNames {

    @Override
    public CapitalDTO mapRow(ResultSet rs, int i) throws SQLException {
        CapitalDTO capital = new CapitalDTO();

        capital.setCapitalId(new BigInteger(rs.getString(CAPITAL_ID)));
        capital.setMontoEntrada(new BigDecimal(rs.getString(CAPITAL_MONTO_ENTRADA)));
        capital.setMontoSalida(new BigDecimal(rs.getString(CAPITAL_MONTO_SALIDA)));
        capital.setDiaMovimiento((rs.getTimestamp(CAPITAL_DIA_MOVIMIENTO)));

        return capital;
    }

}
