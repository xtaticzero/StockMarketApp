/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public class CapaMapper implements RowMapper<CapaDTO> {

    @Override
    public CapaDTO mapRow(ResultSet rs, int i) throws SQLException {

        CapaDTO capa = new CapaDTO();

        capa.setCapa_id(new BigInteger(rs.getString("CAPA_ID")));
//        capa.set(rs.getString("ACCION_ID")));

        EmisoraDTO emisora = new EmisoraDTO();
        emisora.setEmisora_id(new BigInteger(rs.getString("EMISORA_ID")));
        capa.setEmisora(emisora);

        capa.setActivo(rs.getBoolean("ACTIVO"));

        return capa;
    }

}
