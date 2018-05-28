/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.CapitalService;
import com.xtaticzero.systems.dao.CapitalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Service("capitalService")
public class CapitalServiceImpl extends BaseBusinessServices implements CapitalService{

    private static final long serialVersionUID = -6632021297762457642L;

    @Autowired
    @Qualifier("capitalDAO")
    private CapitalDAO capitalDAO;

    @Override
    public CapitalDTO getCapital() throws BusinessException{
        try {
            return capitalDAO.getCapital();
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo obtener el capital actual ");
        }
    }
    
    @Override
    public CapitalDTO agregarEntrada(CapitalDTO entradaCapital) throws BusinessException {
        try {
            return capitalDAO.agregarEntrada(entradaCapital);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo agregar la entrada de capital ");
        }
    }

    @Override
    public CapitalDTO agregarSalida(CapitalDTO salidaCapital) throws BusinessException {
        try {
            return capitalDAO.agregarSalida(salidaCapital);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo guardar la salida de capital ");
        }
    }

}
