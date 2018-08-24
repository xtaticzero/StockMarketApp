/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.IpcDao;
import com.xtaticzero.systems.dao.preparedstatement.IpcPreparedStatement;
import com.xtaticzero.systems.dao.sql.IpcSQL;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class IpcDaoImpl extends BaseJDBCDao<IPCDto> implements IpcDao, IpcSQL {

    private static final long serialVersionUID = 1729539649617850312L;

    @Override
    public IPCDto insert(IPCDto newIpc) throws DAOException {

        if (newIpc == null || newIpc.getValorIPC() == null) {
            throw new DAOException(ERR_GENERAL);
        }

        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new IpcPreparedStatement(newIpc);
            getJdbcTemplateBase().update(statement, keyHolder);
            newIpc.setIndiceCotizacion_id(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return newIpc;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public IPCDto findByYear(Date date) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<IPCDto> findAll(boolean active) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
