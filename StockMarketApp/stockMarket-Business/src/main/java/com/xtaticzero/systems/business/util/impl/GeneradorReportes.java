/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util.impl;

import com.xtaticzero.systems.business.exception.ReporterJasperException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import mx.gob.sat.mat.tabacos.constants.Constantes;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public final class GeneradorReportes {

    /**
     * String de error para formato no soportado.
     */
    public static final String ERROR_TIPO_REPORTE_NO_SOPORTADO = "jasper.exception.general";

    private GeneradorReportes() {

    }

    /**
     * M&eacute;todo generico para crear reportes.
     *
     * @param reportIS
     * @param nombreReporte nombre del reporte con todo y su extenci&oacute;n
     * (.xls o .pdf). Ej. miReporte.pdf
     * @param parametros Mapa con los parametros que estaran en el reporte.
     * @param detalle Lista de mapas para insertar en la banda detail del
     * reporte.
     * @return arreglo de bytes del reporte generado.
     * @throws com.xtaticzero.systems.business.exception.ReporterJasperException
     */
    public static byte[] crearReporte(InputStream reportIS, String nombreReporte,
            Map<String, Object> parametros,
            List<?> detalle) throws ReporterJasperException {

        ReporteJasperUtil reporte = new ReporteJasperUtil();
        reporte.setReporteJasper(reportIS);
        reporte.setNombreReporte(nombreReporte);

        if (nombreReporte.endsWith(Constantes.EXCEL_ANTES_2007)) {
            reporte.setFormatoReporte(ReporteJasperUtil.XLS);
        } else if (nombreReporte.endsWith(Constantes.ARCHIVO_PDF)) {
            reporte.setFormatoReporte(ReporteJasperUtil.PDF);
        } else if (nombreReporte.endsWith(Constantes.ARCHIVO_CSV)) {
            reporte.setFormatoReporte(ReporteJasperUtil.CSV);
        } else {
            throw new ReporterJasperException(GeneradorReportes.ERROR_TIPO_REPORTE_NO_SOPORTADO);
        }

        reporte.setParametrosReporte(parametros);
        reporte.setDatosReporte(detalle);
        try {
            return reporte.generarBytesReporte();
        } catch (ReporterJasperException cex) {
            if (cex.getMessage() != null) {
                throw new ReporterJasperException(ERROR_TIPO_REPORTE_NO_SOPORTADO, cex);
            } else {
                throw new ReporterJasperException(ERROR_TIPO_REPORTE_NO_SOPORTADO, cex);
            }
        }
    }
}
