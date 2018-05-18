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
import static com.xtaticzero.systems.dao.sql.CapaSQL.EXISTE_CAPA_ACTIVA;
import static com.xtaticzero.systems.dao.sql.CapaSQL.FIND_CAPA_BY_ID;
import java.math.BigInteger;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("capaDAO")
public class CapaDAOImpl extends BaseJDBCDao<CapaDTO> implements CapaDAO {

    private static final long serialVersionUID = -1362766772708755379L;

    @Override
    public CapaDTO insert(CapaDTO nuevaCapa) throws DAOException {
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
    public Integer existeCapaActiva(BigInteger idEmisora) throws DAOException {
        try {
            Integer id = getJdbcTemplateBase().queryForObject(EXISTE_CAPA_ACTIVA, new Object[]{idEmisora}, Integer.class);
            if (id != null) {
                return id;
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
    }

    @Override
    public CapaDTO findEmisoraById(CapaDTO emisora) throws DAOException {
        try {
            List<CapaDTO> lstResult = getJdbcTemplateBase().query(FIND_CAPA_BY_ID, new CapaMapper(), emisora.getCapa_id());
            if (lstResult != null && !lstResult.isEmpty()) {
                return lstResult.get(0);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
        return null;
    }
}
