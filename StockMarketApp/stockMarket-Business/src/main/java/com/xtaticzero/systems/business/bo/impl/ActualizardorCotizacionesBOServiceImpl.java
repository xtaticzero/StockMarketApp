/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.bo.ActualizardorCotizacionesBOService;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("updateCotizacionesBOService")
public class ActualizardorCotizacionesBOServiceImpl extends BaseBusinessServices implements ActualizardorCotizacionesBOService {

    private static final long serialVersionUID = -8104145302717724704L;
    
    @Autowired
    @Qualifier("cotizacionDiariaDAO")
    private CotizacionDiariaDAO cotizacionDiariaDAO;

    @Override
    public boolean actualizarCotizacionesAlDia() throws BusinessException {
        try {
            List<CotizacionDiariaDTO> lstResult = cotizacionDiariaDAO.findAllCotizacionDiaria();
            int[] responseArray = cotizacionDiariaDAO.updateBatch(lstResult);
            return (responseArray.length == lstResult.size());
        } catch (DAOException ex) {
            logger.error(ex.getMessage(),ex.getCause());
            return false;
        }
    }

}
