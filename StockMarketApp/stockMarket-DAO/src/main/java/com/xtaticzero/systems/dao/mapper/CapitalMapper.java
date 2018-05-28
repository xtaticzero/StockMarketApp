/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CapitalDTO;
import static com.xtaticzero.systems.dao.util.RowsNames.EMISORA_FECHA_BAJA;
import static com.xtaticzero.systems.dao.util.RowsNames.EMISORA_FECHA_ENTRADA;
import static com.xtaticzero.systems.dao.util.RowsNames.EMISORA_ID;
import static com.xtaticzero.systems.dao.util.RowsNames.EMISORA_NOMBRE;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapitalMapper extends BaseAbstractMapper implements RowMapper<CapitalDTO> {

    @Override
    public CapitalDTO mapRow(ResultSet rs, int i) throws SQLException {
        CapitalDTO capital = new CapitalDTO();

        capital.setCapitalId(new BigInteger(rs.getString(CAPITAL_ID)));
        capital.setMontoEntrada(new BigDecimal(rs.getString(CAPITAL_ENTRADA)));
        capital.setMontoSalida(new BigDecimal(rs.getString(CAPITAL_SALIDA)));
        capital.setDiaMovimiento(rs.getDate(CAPITAL_FECHA));
        
        return capital;
    }

}
