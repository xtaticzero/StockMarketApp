/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util;

import com.xtaticzero.systems.base.enums.ReportsEnum;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.xtaticzero.systems.business.exception.ReporterJasperException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public interface ReporterService {
    
    String MSG_ERROR = "No se pudo generar el reporte";

    byte[] makeReport(ReportsEnum reporteEnum, String nombreReporte,
                                      Map<String, Object> parametros,
                                      List<?> detalle) throws ReporterJasperException;
    
}
