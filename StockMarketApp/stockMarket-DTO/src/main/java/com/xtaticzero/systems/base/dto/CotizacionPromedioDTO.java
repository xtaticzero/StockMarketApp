/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionPromedioDTO extends BaseModel {

    private static final long serialVersionUID = -7970394994605402578L;

    private CotizacionDiariaDTO cotizacionActual;
    private CotizacionHistoricoDTO cotizacionHistoricaLastYear;
    private CotizacionHistoricoDTO cotizacionHistoricaManyYear;

    public CotizacionDiariaDTO getCotizacionActual() {
        return cotizacionActual;
    }

    public void setCotizacionActual(CotizacionDiariaDTO cotizacionActual) {
        this.cotizacionActual = cotizacionActual;
    }

    public CotizacionHistoricoDTO getCotizacionHistoricaLastYear() {
        return cotizacionHistoricaLastYear;
    }

    public void setCotizacionHistoricaLastYear(CotizacionHistoricoDTO cotizacionHistoricaLastYear) {
        this.cotizacionHistoricaLastYear = cotizacionHistoricaLastYear;
    }

    public CotizacionHistoricoDTO getCotizacionHistoricaManyYear() {
        return cotizacionHistoricaManyYear;
    }

    public void setCotizacionHistoricaManyYear(CotizacionHistoricoDTO cotizacionHistoricaManyYear) {
        this.cotizacionHistoricaManyYear = cotizacionHistoricaManyYear;
    }

    public BigDecimal getPromedioLastYear() {
        if (cotizacionHistoricaLastYear == null) {
            return BigDecimal.ZERO;
        }
        return (cotizacionHistoricaLastYear.getCotizacionDiariaDTO().getCostoAlDia().subtract(cotizacionActual.getCostoAlDia())).divide(cotizacionActual.getCostoAlDia(), MathContext.DECIMAL128);
    }

    public BigDecimal getPromedioManyYear() {
        if (cotizacionHistoricaManyYear == null) {
            return BigDecimal.ZERO;
        }

        return (cotizacionHistoricaManyYear.getCotizacionDiariaDTO().getCostoAlDia().subtract(cotizacionActual.getCostoAlDia())).divide(cotizacionActual.getCostoAlDia(), MathContext.DECIMAL128);
    }

    public String getStyleManyYear() {
        System.err.println("getPromedioManyYear() " + getPromedioManyYear() + ((getPromedioManyYear().compareTo(BigDecimal.ZERO))));
        if ((((getPromedioManyYear().compareTo(BigDecimal.ZERO)))) < 0) {
            return "color:white; background-color: red; font-weight: bold;";
        } else {
            return "color:black;";
        }
    }

    public String getStyleLastYear() {
        System.err.println("getPromedioManyYear() " + getPromedioLastYear() + ((getPromedioLastYear().compareTo(BigDecimal.ZERO))));
        if ((((getPromedioLastYear().compareTo(BigDecimal.ZERO)))) < 0) {
            return "color:white; background-color: red; font-weight: bold;";
        } else {
            return "color:black;";
        }
    }

}
