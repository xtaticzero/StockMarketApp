/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util;

import com.xtaticzero.systems.base.enums.ReportsEnum;
import java.util.List;
import java.util.Map;
import com.xtaticzero.systems.business.exception.ReporterJasperException;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public interface ReporterService {
    
    String MSG_ERROR = "jasper.exception.general";

    byte[] makeReport(ReportsEnum reporteEnum, String nombreReporte,
                                      Map<String, Object> parametros,
                                      List<?> detalle) throws ReporterJasperException;
    
}
