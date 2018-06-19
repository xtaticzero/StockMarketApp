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
import static com.xtaticzero.systems.dao.sql.AccionSQL.UPDATE_ACCION;
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

        if (nuevaCapa != null) {

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
        return null;

    }

    @Override
    public int desactivaCapa(CapaDTO capa) throws DAOException {
        if (capa == null || capa.getCapa_id() == null) {
            throw new DAOException(ERR_GENERAL);
        }
        try {
            List<Object> params = new ArrayList<>();
            params.add(capa.getCapa_id());

            return getJdbcTemplateBase().update(DESACTIVA_CAPA, params.toArray());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
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
    public boolean isExistencia(BigInteger idCapa) throws DAOException {
        try {
            List<Integer> lstResult = getJdbcTemplateBase().queryForList(CapaSQL.EXISTENCIA_X_CAPA, Integer.class, idCapa);
            boolean existe = false;
            for (Integer res : lstResult) {
                if (res > 0) {
                    existe = true;
                }
            }
            return existe;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
    }
}
