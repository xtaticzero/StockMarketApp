/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.AccionDTO;
import com.xtaticzero.systems.dao.AccionDAO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.preparedstatement.AccionPreparedStatement;
import java.math.BigInteger;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Repository("accionDAO")
public class AccionDAOImpl extends BaseJDBCDao<AccionDTO> implements AccionDAO {

    @Override
    public AccionDTO insert(AccionDTO nuevaAccion) throws DAOException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new AccionPreparedStatement(nuevaAccion);
            getJdbcTemplateBase().update(statement, keyHolder);
            nuevaAccion.setAccion_id(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return nuevaAccion;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }

    }

}