/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.CapaDAO;
import com.xtaticzero.systems.dao.mapper.CapaMapper;
import com.xtaticzero.systems.dao.preparedstatement.CapaPreparedStatement;
import com.xtaticzero.systems.dao.sql.CapaSQL;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("capaDAO")
public class CapaDAOImpl extends BaseJDBCDao<CapaDTO> implements CapaDAO, CapaSQL {

    private static final long serialVersionUID = -1362766772708755379L;

    @Override
    public CapaDTO insert(CapaDTO nuevaCapa) throws DAOException {

        if (nuevaCapa != null && findCapa(nuevaCapa) !=null) {
            return findCapa(nuevaCapa);
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new CapaPreparedStatement(nuevaCapa);
            getJdbcTemplateBase().update(statement, keyHolder);
            nuevaCapa.setCapa_id(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return nuevaCapa;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int delete(CapaDTO viejaCapa) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(CapaDTO capa) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BigInteger existeCapaActiva(BigInteger idEmisora) throws DAOException {
        try {
            return getJdbcTemplateBase().queryForObject(CapaSQL.EXISTE_CAPA_ACTIVA, new Object[]{idEmisora}, BigInteger.class);
        } catch (EmptyResultDataAccessException erdae) {
            logger.error(erdae.getMessage());
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
    }

    @Override
    public CapaDTO findEmisoraById(BigInteger idCapa) throws DAOException {
        try {
            List<CapaDTO> lstResult = getJdbcTemplateBase().query(CapaSQL.FIND_CAPA_BY_ID, new CapaMapper(), idCapa);
            if (lstResult != null && !lstResult.isEmpty()) {
                return lstResult.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
        return null;
    }

    @Override
    public CapaDTO findCapa(CapaDTO capa) throws DAOException {
        try {
            if (capa != null && capa.getAccion() != null && capa.getEmisora() != null) {

                List<Object> params = new ArrayList<>();

                params.add(capa.getAccion().getAccion_id() != null ? capa.getAccion().getAccion_id() : BigInteger.ZERO);
                params.add(capa.getEmisora().getEmisora_id() != null ? capa.getEmisora().getEmisora_id() : BigInteger.ZERO);

                return getJdbcTemplateBase().queryForObject(CapaSQL.HEDER_SELECT_CAPA.concat(AND).concat(CapaSQL.ACCION_BY_ID).concat(AND).concat(CapaSQL.EMISORA_BY_ID),
                        params.toArray(), new CapaMapper());
            }
            return null;
        } catch (EmptyResultDataAccessException emty) {
            logger.error(emty.getMessage(), emty);
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
    }
}
