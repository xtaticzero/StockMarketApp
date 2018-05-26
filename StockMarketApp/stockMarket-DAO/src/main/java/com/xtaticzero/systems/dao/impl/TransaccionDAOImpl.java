/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.TransaccionDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.TransaccionDAO;
import com.xtaticzero.systems.dao.mapper.TransaccionMapper;
import com.xtaticzero.systems.dao.preparedstatement.TransaccionPreparedStatement;
import static com.xtaticzero.systems.dao.sql.TransaccionSQL.SELECT_ALL_TRANSACCION;
import java.math.BigInteger;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Repository("transaccionDAO")
public class TransaccionDAOImpl extends BaseJDBCDao<TransaccionDTO> implements TransaccionDAO {

    @Override
    public TransaccionDTO insert(TransaccionDTO nuevaTransaccion) throws DAOException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new TransaccionPreparedStatement(nuevaTransaccion);
            getJdbcTemplateBase().update(statement, keyHolder);
            nuevaTransaccion.setTransaccion_id(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return nuevaTransaccion;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public List<TransaccionDTO> findAllTransaccion() throws DAOException {
        try {
            return getJdbcTemplateBase().query(SELECT_ALL_TRANSACCION, new TransaccionMapper());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new DAOException(ERR_GENERAL, e.getMessage(), e);
        }
    }
}