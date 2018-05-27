/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.ComisionDTO;
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
public class ComisionMapper extends BaseAbstractMapper implements RowMapper<ComisionDTO>, RowsNames {

    @Override
    public ComisionDTO mapRow(ResultSet rs, int i) throws SQLException {
        ComisionDTO comision = new ComisionDTO();

        comision.setComision_id(new BigInteger(rs.getString(COMISION_ID)));
        comision.setPorcentaje(new BigDecimal(rs.getString(COMISION_PORCENTAJE)));
        comision.setDescripcion((rs.getString(COMISION_DESCRIPCION)));
        
        return comision;
    }

}
