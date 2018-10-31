/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.ActualizardorCotizacionesBOService;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import com.xtaticzero.systems.test.base.BaseTest;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionDiariaTest extends BaseTest {
    
    @Autowired
    @Qualifier("updateCotizacionesBOService")
    private ActualizardorCotizacionesBOService updateCotizacionesBOService;
    
    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;
    
    @Autowired
    @Qualifier("cotizacionDiariaDAO")
    private CotizacionDiariaDAO cotizacionDiariaDAO;
    
    private UsuarioDTO usr;
    private CotizacionVectorBO cotizacionBO;
    
    @Before
    public void init() throws BusinessException {
        usr = new UsuarioDTO();
        
        usr.setDisplay_name("admin");
        usr.setPassword("admin123");
        
        usr = userService.logginUser(usr);
        
        cotizacionBO = cotizacionService.getBOCotizacion(usr);
    }
    
    @Test
    @Ignore
    public void actualizar() {
        try {
            updateCotizacionesBOService.actualizarCotizacionesAlDia();
            logger.info("Salio bien");
        } catch (Exception e) {
            logger.error("salio mal");
            logger.error(e.getMessage(), e);
        }
    }
    
    @Test
    //@Ignore
    public void obtenerValores() {
        try {
            List<CotizacionDiariaDTO> lst = cotizacionDiariaDAO.findAllCotizacionDiaria();
            
            if (lst != null) {
                for (CotizacionDiariaDTO cotizacionDiariaDTO : lst) {
                    System.out.println("NOMBRE: ".concat(cotizacionDiariaDTO.getEmisora().getNombre()).concat(" FECHA :").concat(cotizacionDiariaDTO.getDiaCotizacion().toString()));
                }
            }
            
            logger.info("Salio bien");
        } catch (Exception e) {
            logger.error("salio mal");
            logger.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void testpromedio() throws BusinessException {
        cotizacionBO.setYearFiltro(2013);
        cotizacionService.getLstCotizaciones(cotizacionBO);
        
        cotizacionService.calcularPromediosAnuales(cotizacionBO, 2018);
        logger.error(cotizacionBO);
    }
}
