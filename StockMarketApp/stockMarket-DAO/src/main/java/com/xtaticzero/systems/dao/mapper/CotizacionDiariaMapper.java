/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
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
public class CotizacionDiariaMapper implements RowMapper<CotizacionDiariaDTO>,RowsNames{

    @Override
    public CotizacionDiariaDTO mapRow(ResultSet rs, int i) throws SQLException {
        CotizacionDiariaDTO cotizacion = new CotizacionDiariaDTO();
        
        cotizacion.setCotizacionId(new BigInteger(rs.getString(COTIZACION_ID)));
        cotizacion.setCostoAlDia(new BigDecimal(rs.getString(COTIZACION_AL_DIA)));
        cotizacion.setDiaCotizacion((rs.getTimestamp(COTIZACION_FECHA_COTIZACION)));
        cotizacion.setFechaTermino((rs.getTimestamp(COTIZACION_FECHA_BAJA)));
        cotizacion.setEmisora(new EmisoraMapper().mapRow(rs, i));
        
        return cotizacion;
    }    
}
