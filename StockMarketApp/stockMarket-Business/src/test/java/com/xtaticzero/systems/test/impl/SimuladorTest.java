/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.impl.SimuladorBO;
import com.xtaticzero.systems.business.market.SimuladorService;
import com.xtaticzero.systems.test.base.BaseTest;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class SimuladorTest extends BaseTest {

    @Autowired
    @Qualifier("simuladorService")
    private SimuladorService simuladorService;

    private UsuarioDTO usuario;
    private SimuladorBO simuladorBo;

    @Before
    public void init() {
        usuario = new UsuarioDTO();
        usuario.setDisplay_name("operador");
        usuario.setRol_id(new BigInteger("2"));
    }

    @Test
    @Ignore
    public void inicializaciosBOTest() {
        if (simuladorService != null) {
            try {
                simuladorBo = simuladorService.inicializaSimulador(usuario);

                if (simuladorBo.getCotizacionDiariaBo() != null) {
                    for (CotizacionDiariaDTO object : simuladorBo.getCotizacionDiariaBo().getLstCotizacionesDiarias()) {
                        System.out.println(" comision : ".concat(object.getCostoAlDia().toString()));
                    }
                }

            } catch (BusinessException ex) {
                logger.error(ex.getMessage(), ex);
            }
        }
    }

}
