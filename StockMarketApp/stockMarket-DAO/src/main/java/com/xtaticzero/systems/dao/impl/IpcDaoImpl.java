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
import com.xtaticzero.systems.dao.mapper.IpcMapper;
import com.xtaticzero.systems.dao.preparedstatement.IpcPreparedStatement;
import com.xtaticzero.systems.dao.sql.IpcSQL;
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
@Repository("ipcDao")
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
    public IPCDto findLastOfYear() throws DAOException {
        try {

            return getJdbcTemplateBase().queryForObject(FIND_LAST_OF_YEAR, new IpcMapper());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            return null;
        }
    }

    @Override
    public List<IPCDto> findAll(int numYears) throws DAOException {
        try {
            List<Object> params = new ArrayList<>();

            params.add(numYears < 1 ? 1 : numYears);

            return getJdbcTemplateBase().query(FIND_ALL_YEARS, params.toArray(), new IpcMapper());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public int[] inserBatch(final List<IPCDto> lstIpc) throws DAOException {
        return getJdbcTemplateBase().batchUpdate(INSERT_BATCH, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                IPCDto cotizacion = lstIpc.get(i);
                ps.setObject(1, cotizacion.getValorIPC());
                ps.setObject(2, cotizacion.getPorcentajeCotizacion());
                ps.setObject(3, cotizacion.getDiaMovimiento());
            }

            @Override
            public int getBatchSize() {
                return lstIpc.size();
            }
        });
    }

}
