/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util.impl;

import com.xtaticzero.systems.base.enums.ReportsEnum;
import com.xtaticzero.systems.business.BaseBusinessAbstract;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.xtaticzero.systems.business.util.ReporterService;
import mx.gob.sat.mat.tabacos.negocio.excepcion.ConfiguracionJasperException;
import com.xtaticzero.systems.business.exception.ReporterJasperException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
@Service
@Qualifier("reporterService")
public class ReporterServiceImpl extends BaseBusinessAbstract implements ReporterService {

    private static final long serialVersionUID = -1858592023847363133L;

    @Override
    public byte[] makeReport(ReportsEnum reporteEnum, String nombreReporte,
            Map<String, Object> parametros,
            List<?> detalle) throws ReporterJasperException {
        try {
            InputStream fileIS = this.getClass().getClassLoader().getResourceAsStream((reporteEnum.getPath()));
            return GeneradorReportes.crearReporte(fileIS, nombreReporte, parametros, detalle);
        } catch (ConfiguracionJasperException cje) {
            logger.error(cje.getCause(),cje);
            throw new ReporterJasperException(MSG_ERROR, cje);
        }

    }

}
