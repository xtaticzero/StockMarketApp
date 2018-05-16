/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.rules.stockmarket;

import com.xtaticzero.systems.business.bo.impl.CompraVentaBO;
import com.xtaticzero.systems.business.rules.Rule;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum CompraVentaRule implements Rule<CompraVentaBO> {

    COMPRA_VENTA_VALIDA {
        @Override
        public boolean process(CompraVentaBO consultaBO) {
            return (consultaBO != null && consultaBO.getUsuario() != null);
        }
    };

}
