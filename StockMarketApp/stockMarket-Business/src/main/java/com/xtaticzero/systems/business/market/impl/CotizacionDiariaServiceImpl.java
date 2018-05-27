/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("cotizacionDiariaService")
public class CotizacionDiariaServiceImpl extends BaseBusinessServices implements CotizacionDiariaService {

    private static final long serialVersionUID = -6578645948929019735L;

    @Autowired
    @Qualifier("cotizacionDiariaDAO")
    private CotizacionDiariaDAO cotizacionDAO;

    @Override
    public CotizacionVectorBO getBOCotizacion(UsuarioDTO usuario) throws BusinessException {
        if (CotizacionVectorBO.getBOValido(usuario) != null) {
            return CotizacionVectorBO.getBOValido(usuario);
        }
        throw new BusinessException(ERR_GENERAL_DESCRIPCION, DESC_USUARIO_INVALIDO.replace(ATRIBUTO_INTEROGACION, usuario != null && usuario.getDisplay_name() != null ? usuario.getDisplay_name() : "unknown || null"));
    }

    @Override
    public CotizacionVectorBO getLstCotizaciones(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException {
        try {
            if (cotizacionDiariaBO == null) {
                return null;
            }
            cotizacionDiariaBO.setLstCotizacionesDiarias(cotizacionDAO.findAllCotizacionDiaria());
            return cotizacionDiariaBO;
        } catch (DAOException daoEx) {
            logger.error(daoEx.getCause(), daoEx);
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, DESC_ACTUALIZAR_INFO);
        }
    }

    @Override
    public CotizacionVectorBO actualizarCotizacion(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException {
        try {
            if (cotizacionDiariaBO == null) {
                return null;
            }
            if (cotizacionDAO.update(cotizacionDiariaBO.getCotizacionSeleccionada()) == 0) {
                throw new BusinessException(ERR_GENERAL_DESCRIPCION, DESC_ACTUALIZAR_INFO);
            }

            return cotizacionDiariaBO;
        } catch (DAOException daoEx) {
            logger.error(daoEx.getCause(), daoEx);
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, DESC_ACTUALIZAR_INFO);
        }

    }

    @Override
    public CotizacionVectorBO actualizarCotizaciones(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException {
        try {
            if (cotizacionDiariaBO == null) {
                return null;
            }
            if (cotizacionDAO.updateBatch(cotizacionDiariaBO.getLstCotizacionesDiarias()).length == 0) {
                throw new BusinessException(ERR_GENERAL_DESCRIPCION, DESC_ACTUALIZAR_INFO);
            }
            return cotizacionDiariaBO;
        } catch (DAOException daoEx) {
            logger.error(daoEx.getCause(), daoEx);
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, DESC_ACTUALIZAR_INFO);
        }
    }

    @Override
    public CotizacionDiariaDTO findCotizacionDiariaByEmisora(BigInteger idEmisora) throws BusinessException {
        try {
            return cotizacionDAO.findCotizacionDiariaByEmisora(idEmisora);
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo obtener la cotizacion diaria ");
        }
    }

}
