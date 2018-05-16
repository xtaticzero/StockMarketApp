/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.EmisoraDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class EmisoraMapper implements RowMapper<EmisoraDTO> {

    protected static final String EMISORA_ID = "EMI.emisora_id";
    protected static final String EMISORA_NOMBRE = "EMI.nombre";
    protected static final String EMISORA_FECHA_ENTRADA = "EMI.fechaEntrada";
    protected static final String EMISORA_FECHA_BAJA = "EMI.fechaBaja";

    @Override
    public EmisoraDTO mapRow(ResultSet rs, int i) throws SQLException {
        EmisoraDTO emisoira = new EmisoraDTO();
        
        emisoira.setEmisora_id(new BigInteger(rs.getString(EMISORA_ID)));
        emisoira.setNombre((rs.getString(EMISORA_NOMBRE)));
        emisoira.setFechaEntrada((rs.getTimestamp(EMISORA_FECHA_ENTRADA)));
        emisoira.setFechaBaja((rs.getTimestamp(EMISORA_FECHA_BAJA)));
        
        return emisoira;
    }

}
