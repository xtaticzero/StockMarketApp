/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.AccionDTO;
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
public class AccionMapper extends BaseAbstractMapper implements RowMapper<AccionDTO>, RowsNames {

    @Override
    public AccionDTO mapRow(ResultSet rs, int i) throws SQLException {
        AccionDTO accion = new AccionDTO();

        accion.setAccion_id(new BigInteger(rs.getString(ACCION_ID)));
        accion.setCostoUnitario(new BigDecimal(rs.getString(ACCION_COSTO_UNITARIO)));
        accion.setFechaCompra((rs.getTimestamp(ACCION_FECHA_COMPRA)));
        accion.setFechaVenta((rs.getTimestamp(ACCION_FECHA_VENTA)));

        return accion;
    }

}
