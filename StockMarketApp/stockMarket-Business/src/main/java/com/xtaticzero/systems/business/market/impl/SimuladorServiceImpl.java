/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.ComisionDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.bo.impl.SimuladorBO;
import com.xtaticzero.systems.business.market.CapaAccionService;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.business.market.SimuladorService;
import com.xtaticzero.systems.business.rules.stockmarket.SimuladorEnumRule;
import com.xtaticzero.systems.dao.ComisionDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("simuladorService")
public class SimuladorServiceImpl extends BaseBusinessServices implements SimuladorService {

    private static final long serialVersionUID = 8598601570393125667L;

    @Autowired
    @Qualifier("comisionDAO")
    private ComisionDAO comisionDAO;

    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionDiariaService;

    @Autowired
    @Qualifier("capaAccionService")
    private CapaAccionService capaAccionService;

    @Override
    public SimuladorBO inicializaSimulador(UsuarioDTO usuario) throws BusinessException {
        SimuladorBO simuladorBo = SimuladorBO.getBOValido(usuario);

        if (simuladorBo != null) {
            try {
                simuladorBo.setCotizacionDiariaBo(cotizacionDiariaService.getBOCotizacion(usuario));
                cotizacionDiariaService.getLstCotizaciones(simuladorBo.getCotizacionDiariaBo());
                List<ComisionDTO> lstComisiones = comisionDAO.findComision();
                simuladorBo.setComision(lstComisiones != null && !lstComisiones.isEmpty() ? lstComisiones.get(0) : null);
                simuladorBo.setLstCapaAccion(capaAccionService.obtenerCapaAcciones());
                
                simuladorBo.setRule(SimuladorEnumRule.INICIALIZACION_VALIDA);
                if(!simuladorBo.getRule().process(simuladorBo)){
                    throw new BusinessException(ERR_GENERAL_DESCRIPCION,"No se creo SimuladorBO correctamente");
                }                
            } catch (DAOException ex) {
                logger.error(ex.getCause(), ex);
                throw new BusinessException(ERR_GENERAL_DESCRIPCION, ex.getMessage(), ex);
            }
        }
        return simuladorBo;
    }

}
