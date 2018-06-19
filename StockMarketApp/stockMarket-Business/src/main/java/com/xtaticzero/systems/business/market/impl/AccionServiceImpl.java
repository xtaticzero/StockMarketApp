/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.AccionDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.AccionService;
import com.xtaticzero.systems.dao.AccionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Service("accionService")
public class AccionServiceImpl extends BaseBusinessServices implements AccionService {

    private static final long serialVersionUID = -5176742198684916246L;

    @Autowired
    @Qualifier("accionDAO")
    private AccionDAO accionDAO;

    @Override
    public AccionDTO guardarAccion(AccionDTO nuevaAccion) throws BusinessException {
        try {
            return accionDAO.insert(nuevaAccion);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo guardar la accion ");
        }
    }

    @Override
    public Integer update(AccionDTO updateAccion) throws BusinessException {
        try {
            return accionDAO.update(updateAccion);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo actualizar la accion ");
        }
    }

}
