/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.TransaccionDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.TransaccionService;
import com.xtaticzero.systems.dao.TransaccionDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Service("transaccionService")
public class TransaccionServiceImpl extends BaseBusinessServices implements TransaccionService {

    @Autowired
    @Qualifier("transaccionDAO")
    private TransaccionDAO transaccionDAO;

    @Override
    public TransaccionDTO guardarTransaccion(TransaccionDTO nuevaTransaccion) throws BusinessException {
        try {
            return transaccionDAO.insert(nuevaTransaccion);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo guardar la transaccion ");
        }
    }

    @Override
    public List<TransaccionDTO> obtenerTransacciones() throws BusinessException {
        try {
            return transaccionDAO.findAllTransaccion();
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se puede obtener lista de transacciones ");
        }
    }

}
