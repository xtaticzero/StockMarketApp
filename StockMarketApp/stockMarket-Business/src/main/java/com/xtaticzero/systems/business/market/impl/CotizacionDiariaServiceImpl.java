/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.business.util.ExcelReaderService;
import com.xtaticzero.systems.business.util.TipoArchivoCargaEnum;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import com.xtaticzero.systems.dao.IpcDao;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
    @Qualifier("excelReaderService")
    private ExcelReaderService excelReaderService;

    @Autowired
    @Qualifier("cotizacionDiariaDAO")
    private CotizacionDiariaDAO cotizacionDAO;

    @Autowired
    @Qualifier("ipcDao")
    private IpcDao ipcDao;

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

    @Override
    public CotizacionVectorBO findCotizacionDiariaHistoricoByEmisora(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException {
        try {
            if (cotizacionDiariaBO != null && cotizacionDiariaBO.getCotizacionSeleccionada() != null && cotizacionDiariaBO.getCotizacionSeleccionada().getEmisora().getEmisora_id() != null) {
                cotizacionDiariaBO.setLstHistoricoCotizacion(cotizacionDAO.findCotizacionHistoryByEmisoraCurrentYear(cotizacionDiariaBO.getCotizacionSeleccionada().getEmisora().getEmisora_id() != null ? cotizacionDiariaBO.getCotizacionSeleccionada().getEmisora().getEmisora_id() : BigInteger.ZERO));

                return cotizacionDiariaBO;
            }
            if (cotizacionDiariaBO != null) {
                cotizacionDiariaBO.setLstHistoricoCotizacion(new ArrayList<CotizacionHistoricoDTO>());
            }

            return cotizacionDiariaBO;
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo obtener la cotizacion diaria ");
        }
    }

    @Override
    public CotizacionVectorBO getLstIPC(CotizacionVectorBO cotizacionDiariaBO, Integer numYears) throws BusinessException {
        try {

            if (cotizacionDiariaBO == null) {
                return null;
            }

            cotizacionDiariaBO.setLstIpc(ipcDao.findAll(numYears != null ? numYears : 0));

            return cotizacionDiariaBO;
        } catch (Exception daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "No se pudo obtener la cotizacion diaria ");
        }
    }

    @Override
    public CotizacionVectorBO calcularPromediosAnuales(CotizacionVectorBO cotizacionDiariaBO, Date anioEnCurso) throws BusinessException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CotizacionVectorBO insertIPCFromExcel(CotizacionVectorBO cotizacionDiariaBO, File fileCarga, InputStream fileIS) throws BusinessException {
        try {

            if (fileCarga != null) {
                cotizacionDiariaBO.setLstIpcCargaExcel((List<IPCDto>) excelReaderService.getLstOfIpc(fileCarga,TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC));
            } else if (fileIS != null) {
                cotizacionDiariaBO.setLstIpcCargaExcel((List<IPCDto>) excelReaderService.getLstOfIpc(fileIS,TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC));
            }

            int[] result = ipcDao.inserBatch(cotizacionDiariaBO.getLstIpcCargaExcel());
            logger.debug(result);
            return cotizacionDiariaBO;
        } catch (DAOException daoEx) {
            logger.error(daoEx.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, daoEx, "Error al cargar datos del Excel");
        } catch (IOException ex) {
            logger.error(ex);
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, ex, "Error en el archivo cargado");
        } catch (InvalidFormatException ex) {
            logger.error(ex);
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, ex, "Formato invalido del archivo");
        }
    }

}
