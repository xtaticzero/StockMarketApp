/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.business.market.EmisoraService;
import com.xtaticzero.systems.test.base.BaseTest;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class EmisoraTest extends BaseTest {

    @Autowired
    @Qualifier("emisoraService")
    private EmisoraService emisoraService;
    
    private EmisoraDTO newEmisora;
    
    @Before
    public void init(){
        newEmisora = new EmisoraDTO();
        newEmisora.setNombre("BIMBO");        
    }
    
    @Test
    public void puebaInsert(){
        try {
            String nombre = emisoraService.guardarEmisora(newEmisora).getNombre();
            logger.info(nombre);
        } catch (BusinessException ex) {
            logger.error(ex.getMessage(),ex);
        }
    }

}
