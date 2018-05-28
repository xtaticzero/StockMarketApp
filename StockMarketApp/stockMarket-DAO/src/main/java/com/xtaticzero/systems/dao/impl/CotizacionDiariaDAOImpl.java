/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import com.xtaticzero.systems.dao.mapper.CotizacionDiariaMapper;
import com.xtaticzero.systems.dao.mapper.CotizacionHistoryMapper;
import com.xtaticzero.systems.dao.preparedstatement.CotizacionDiariaPreparedStatement;
import com.xtaticzero.systems.dao.sql.CotizacionDiariaSQL;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("cotizacionDiariaDAO")
public class CotizacionDiariaDAOImpl extends BaseJDBCDao<CotizacionDiariaDTO> implements CotizacionDiariaDAO, CotizacionDiariaSQL {

    private static final long serialVersionUID = -7960526923843331731L;

    @Override
    public CotizacionDiariaDTO insert(CotizacionDiariaDTO cotizacionDiariaNew) throws DAOException {
        CotizacionDiariaDTO cotizacionTmp;
        if (cotizacionDiariaNew == null || cotizacionDiariaNew.getCostoAlDia() == null || cotizacionDiariaNew.getEmisora() == null) {
            throw new DAOException(ERR_GENERAL);
        }

        if (findCotizacionDiariaByEmisora(cotizacionDiariaNew.getEmisora().getEmisora_id()) != null) {
            cotizacionTmp = findCotizacionDiariaByEmisora(cotizacionDiariaNew.getEmisora().getEmisora_id());
            cotizacionTmp.setCostoAlDia(cotizacionDiariaNew.getCostoAlDia());
            if (update(cotizacionTmp) > 0) {
                return cotizacionTmp;
            }
            throw new DAOException(ERR_GENERAL);
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new CotizacionDiariaPreparedStatement(cotizacionDiariaNew);
            getJdbcTemplateBase().update(statement, keyHolder);
            cotizacionDiariaNew.setCotizacionId(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return cotizacionDiariaNew;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int update(CotizacionDiariaDTO cotizacionDiaria) throws DAOException {
        if (cotizacionDiaria == null || cotizacionDiaria.getCostoAlDia() == null || cotizacionDiaria.getEmisora() == null) {
            throw new DAOException(ERR_GENERAL);
        }

        try {
            List<Object> params = new ArrayList<>();

            params.add(cotizacionDiaria.getCostoAlDia());
            params.add(cotizacionDiaria.getEmisora().getEmisora_id());

            return getJdbcTemplateBase().update(UPDATE_COTIZACION, params.toArray());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int delete(CotizacionDiariaDTO cotizacionDiaria) throws DAOException {
        if (cotizacionDiaria == null || cotizacionDiaria.getCostoAlDia() == null || cotizacionDiaria.getEmisora() == null) {
            throw new DAOException(ERR_GENERAL);
        }

        try {
            List<Object> params = new ArrayList<>();

            params.add(cotizacionDiaria.getEmisora().getEmisora_id());
            params.add(cotizacionDiaria.getCotizacionId());

            return getJdbcTemplateBase().update(DELETE_COTIZACION, params.toArray());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public CotizacionDiariaDTO findCotizacionDiariaByEmisora(BigInteger idEmisora) throws DAOException {
        if (idEmisora == null) {
            throw new DAOException(ERR_GENERAL);
        }
        try {
            List<Object> params = new ArrayList<>();
            params.add(idEmisora);
            List<CotizacionDiariaDTO> lstResult = getJdbcTemplateBase()
                    .query(FIND_COTIZACION_BY_EMISORA, params.toArray(),
                            new CotizacionDiariaMapper());
            if (lstResult != null && !lstResult.isEmpty()) {
                return lstResult.get(0);
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }

        return null;
    }

    @Override
    public List<CotizacionHistoricoDTO> findCotizacionHistoryByEmisora(BigInteger idEmisora) throws DAOException {
        try {
            List<Object> params = new ArrayList<>();
            params.add(idEmisora);

            return getJdbcTemplateBase().query(FIND_COTIZACION_HISTORY_BY_EMISORA, params.toArray(),new CotizacionHistoryMapper());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public List<CotizacionDiariaDTO> findAllCotizacionDiaria() throws DAOException {
        try {

            return getJdbcTemplateBase().query(FIND_COTIZACION_ALL, new CotizacionDiariaMapper());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }

    }

    @Override
    public int[] updateBatch(final List<CotizacionDiariaDTO> lstCotizaciones) throws DAOException {

        return getJdbcTemplateBase().batchUpdate(UPDATE_COTIZACION, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                CotizacionDiariaDTO cotizacion = lstCotizaciones.get(i);
                ps.setObject(1, cotizacion.getCostoAlDia());
                ps.setObject(2, cotizacion.getEmisora().getEmisora_id());
                ps.setObject(3, cotizacion.getCotizacionId());
            }

            @Override
            public int getBatchSize() {
                return lstCotizaciones.size();
            }
        });
    }

}
