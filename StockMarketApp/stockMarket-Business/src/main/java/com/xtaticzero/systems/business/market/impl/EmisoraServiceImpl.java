/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.EmisoraService;
import com.xtaticzero.systems.dao.EmisoraDAO;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("emisoraService")
public class EmisoraServiceImpl extends BaseBusinessServices implements EmisoraService {

    private static final long serialVersionUID = -5176742198684916246L;
    
    @Autowired
    @Qualifier("emisoraDAO")
    private EmisoraDAO emisoraDAO;

    @Override
    public EmisoraDTO guardarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException {
        try {
            if (nuevaEmisora == null) {
                return nuevaEmisora;
            }
            
            emisoraDAO.insert(nuevaEmisora);
            
            return nuevaEmisora;
            
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION,daoEx,"No se pudo guardar la emisora con nombre : ".concat(nuevaEmisora.getNombre()));
        }
    }

    @Override
    public Boolean desactivarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean actualizarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<EmisoraDTO> obtenerEmisoras() throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
