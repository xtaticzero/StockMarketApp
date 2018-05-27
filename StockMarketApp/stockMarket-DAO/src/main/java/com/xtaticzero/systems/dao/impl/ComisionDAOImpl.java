/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.dao.mapper.ComisionMapper;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.ComisionDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.ComisionDAO;
import com.xtaticzero.systems.dao.preparedstatement.ComisionPrepareStatement;
import com.xtaticzero.systems.dao.sql.ComisionSQL;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class ComisionDAOImpl extends BaseJDBCDao<ComisionDTO> implements ComisionDAO, ComisionSQL {

    private static final long serialVersionUID = 8822898927628723785L;

    @Override
    public ComisionDTO insertComision(ComisionDTO newComision) throws DAOException {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new ComisionPrepareStatement(newComision);
            getJdbcTemplateBase().update(statement, keyHolder);
            newComision.setComision_id(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return newComision;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int updateComision(ComisionDTO comision) throws DAOException {
        if (comision != null && (comision.getComision_id() == null)) {
            return 0;
        } else {
            try {
                List<Object> params = new ArrayList<>();

                params.add(comision.getPorcentaje());
                params.add(comision.getDescripcion());
                params.add(comision.getComision_id());

                return getJdbcTemplateBase().update(UPDATE_COMISION, params.toArray());

            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
                throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
            }
        }
    }

    @Override
    public ComisionDTO deleteComision(ComisionDTO comision) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ComisionDTO> findComision() throws DAOException {
        try {

            return getJdbcTemplateBase().query(UPDATE_COMISION, new ComisionMapper());

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

}
