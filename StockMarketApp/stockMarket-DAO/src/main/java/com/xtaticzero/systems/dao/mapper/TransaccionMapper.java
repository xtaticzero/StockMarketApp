/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.TransaccionDTO;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class TransaccionMapper extends BaseAbstractMapper implements RowMapper<TransaccionDTO> {

    @Override
    public TransaccionDTO mapRow(ResultSet rs, int i) throws SQLException {

        TransaccionDTO transaccion = new TransaccionDTO();
        transaccion.setTransaccion_id(new BigInteger(rs.getString(TRANSACCION_ID)));
        transaccion.setCapaAccion(new CapaAccionMapper().mapRow(rs, i));
        transaccion.setMovimiento(new MovimientoMapper().mapRow(rs, i));
        transaccion.setFechaTransaccion(rs.getDate(TRANSACCION_FECHA));
        transaccion.setCantidad(rs.getInt(TRANSACCION_CANTIDAD));
        transaccion.setCostoUnitario(new BigDecimal(rs.getString(TRANSACCION_COSTO)));
        transaccion.setTotal(new BigDecimal(rs.getString(TRANSACCION_TOTAL)));
        transaccion.setUtilidad(new BigDecimal(rs.getString(TRANSACCION_UTILIDAD)));
        transaccion.setPorcentajeMovimiento(new BigDecimal(rs.getString(TRANSACCION_PORCENTAJE)));

        return transaccion;
    }

}
