/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.rules.stockmarket;

import com.xtaticzero.systems.business.bo.impl.SimuladorBO;
import com.xtaticzero.systems.business.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum SimuladorEnumRule implements Rule<SimuladorBO> {
    INICIALIZACION_VALIDA {
        @Override
        public boolean process(SimuladorBO simuladorBO) {
            return (simuladorBO.getComision() != null
                    && simuladorBO.getLstCapaAccion() != null
                    && !simuladorBO.getLstCapaAccion().isEmpty()
                    && simuladorBO.getCotizacionDiariaBo() != null);
        }
    };
}
