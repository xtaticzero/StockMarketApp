/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.rules.stockmarket;

import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum CotizacionDiariaRule implements Rule<CotizacionVectorBO>{
    COSTO_VALIDO {
        @Override
        public boolean process(CotizacionVectorBO cotizacionBO) {
            return (cotizacionBO != null && cotizacionBO.getUsuario() != null);
        }
    };
}
