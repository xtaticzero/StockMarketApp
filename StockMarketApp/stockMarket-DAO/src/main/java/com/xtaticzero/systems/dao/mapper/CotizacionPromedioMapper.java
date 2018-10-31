/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;


import com.xtaticzero.systems.base.dto.CotizacionPromedioDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;


/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionPromedioMapper implements RowMapper<CotizacionPromedioDTO> {

    @Override
    public CotizacionPromedioDTO mapRow(ResultSet rs, int i) throws SQLException {
        CotizacionPromedioDTO cotizacionPromedio = new CotizacionPromedioDTO();
        
        cotizacionPromedio.setCotizacionActual(new CotizacionDiariaMapper().mapRow(rs, i));
        cotizacionPromedio.setCotizacionActual(new CotizacionDiariaMapper().mapRow(rs, i));
        
        return cotizacionPromedio;
    }

}
