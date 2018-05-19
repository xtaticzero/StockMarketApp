/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.CapaAccionService;
import com.xtaticzero.systems.dao.CapaAccionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Service("capaAccionService")
public class CapaAccionServiceImpl extends BaseBusinessServices implements CapaAccionService {
    
    @Autowired
    @Qualifier("capaAccionDAO")
    private CapaAccionDAO capaAccionDAO;

    @Override
    public CapaAccionDTO guardarAccion(CapaAccionDTO nuevaAccion) throws BusinessException {
         try {
            return capaAccionDAO.insert(nuevaAccion);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo guardar la relacion capa-accion ");
        }
    }

}
