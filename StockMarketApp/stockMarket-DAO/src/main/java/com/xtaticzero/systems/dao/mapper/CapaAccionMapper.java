/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapaAccionMapper extends BaseAbstractMapper implements RowMapper<CapaAccionDTO> {

    @Override
    public CapaAccionDTO mapRow(ResultSet rs, int i) throws SQLException {
        
        CapaAccionDTO capaAcc = new CapaAccionDTO();
        capaAcc.setCaId(new BigInteger(rs.getString(CAPA_ACCION_ID)));
        capaAcc.setCapa(new CapaMapper().mapRow(rs, i));
        capaAcc.setAccion(new AccionMapper().mapRow(rs, i));
        
        return capaAcc;
        
    }

}
