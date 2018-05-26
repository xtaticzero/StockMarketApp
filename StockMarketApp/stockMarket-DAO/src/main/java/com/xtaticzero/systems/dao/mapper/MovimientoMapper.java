/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.MovimientoDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class MovimientoMapper extends BaseAbstractMapper implements RowMapper<MovimientoDTO> {

    @Override
    public MovimientoDTO mapRow(ResultSet rs, int i) throws SQLException {
        MovimientoDTO movimiento = new MovimientoDTO();
        movimiento.setMovimiento_id(new BigInteger(rs.getString(MOVIMIENTO_ID)));
        movimiento.setDescripcion(rs.getString(MOVIMIENTO_DESC));

        return movimiento;
    }

}
