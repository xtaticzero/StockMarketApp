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
import static com.xtaticzero.systems.dao.sql.AccionSQL.UPDATE_ACCION;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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

    private static final long serialVersionUID = 8310743835188211947L;

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

    @Override
    public Integer update(AccionDTO updateAccion) throws DAOException {
        if (updateAccion == null || updateAccion.getAccion_id() == null || updateAccion.getExistencia() == null) {
            throw new DAOException(ERR_GENERAL);
        }
        try {
            List<Object> params = new ArrayList<>();

            params.add(updateAccion.getExistencia());
            params.add(updateAccion.getAccion_id());

            return getJdbcTemplateBase().update(UPDATE_ACCION, params.toArray());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

}
