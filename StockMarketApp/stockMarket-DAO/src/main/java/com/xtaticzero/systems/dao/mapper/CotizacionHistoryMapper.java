/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
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
public class CotizacionHistoryMapper extends BaseAbstractMapper implements RowMapper<CotizacionHistoricoDTO>, RowsNames {
    
    @Override
    public CotizacionHistoricoDTO mapRow(ResultSet rs, int i) throws SQLException {
        CotizacionHistoricoDTO cotizacionHistorico = new CotizacionHistoricoDTO();
        
        cotizacionHistorico.setCotizacionHistoryId(new BigInteger(rs.getString(COTIZACION_HISTORY_ID)));
        cotizacionHistorico.setCotizacionDiariaDTO(new CotizacionDiariaMapper().mapRow(rs, i));
        cotizacionHistorico.getCotizacionDiariaDTO().setCostoAlDia(new BigDecimal(rs.getString(COTIZACION_HISTORY_COSTO)));
        cotizacionHistorico.getCotizacionDiariaDTO().setDiaCotizacion(rs.getDate(COTIZACION_HISTORY_FECHA));
        
        return cotizacionHistorico;
    }
    
}
