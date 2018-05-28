/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigInteger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionHistoricoDTO extends BaseModel {

    private static final long serialVersionUID = 574332160391117556L;

    private BigInteger cotizacionHistoryId;
    private CotizacionDiariaDTO cotizacionDiariaDTO;

    public BigInteger getCotizacionHistoryId() {
        return cotizacionHistoryId;
    }

    public void setCotizacionHistoryId(BigInteger cotizacionHistoryId) {
        this.cotizacionHistoryId = cotizacionHistoryId;
    }

    public CotizacionDiariaDTO getCotizacionDiariaDTO() {
        return cotizacionDiariaDTO;
    }

    public void setCotizacionDiariaDTO(CotizacionDiariaDTO cotizacionDiariaDTO) {
        this.cotizacionDiariaDTO = cotizacionDiariaDTO;
    }

}
