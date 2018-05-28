/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.CapitalDAO;
import com.xtaticzero.systems.dao.mapper.CapitalMapper;
import com.xtaticzero.systems.dao.preparedstatement.CapitalPrepareStatement;
import com.xtaticzero.systems.dao.util.RowsNames;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("capitalDAO")
public class CapitalDAOImpl extends BaseJDBCDao<CapitalDTO> implements CapitalDAO {

    private static final long serialVersionUID = -2124018714836352194L;

    @Override
    public CapitalDTO agregarEntrada(CapitalDTO entradaCapital) throws DAOException {
        if (entradaCapital == null || entradaCapital.getMontoEntrada() == null) {
            return null;
        }
        entradaCapital.setMontoSalida(null);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new CapitalPrepareStatement(entradaCapital);
            getJdbcTemplateBase().update(statement, keyHolder);
            entradaCapital.setCapitalId(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return entradaCapital;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }

    }

    @Override
    public CapitalDTO agregarSalida(CapitalDTO salidaCapital) throws DAOException {
        if (salidaCapital == null || salidaCapital.getMontoSalida() == null) {
            return null;
        }
        salidaCapital.setMontoEntrada(null);
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            PreparedStatementCreator statement
                    = new CapitalPrepareStatement(salidaCapital);
            getJdbcTemplateBase().update(statement, keyHolder);
            salidaCapital.setCapitalId(new BigInteger(String.valueOf(keyHolder.getKey().intValue())));
            return salidaCapital;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            throw new DAOException(ERR_GENERAL, ex.getMessage(), ex);
        }
    }

    @Override
    public BigDecimal totalEntradas() throws DAOException {
        try {
            BigDecimal totalEntradas = BigDecimal.ZERO;
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(TOTAL_ENTRADAS);

            while (srs.next()) {
                totalEntradas = new BigDecimal(srs.getString(RowsNames.TOTAL));
            }

            return totalEntradas;
        } catch (Exception e) {
            logger.error(e);
            throw new DAOException(ERR_GENERAL, e);
        }

    }

    @Override
    public BigDecimal totalSalidas() throws DAOException {
        try {
            BigDecimal totalSalidas = BigDecimal.ZERO;
            SqlRowSet srs = getJdbcTemplateBase().queryForRowSet(TOTAL_SALIDAS);

            while (srs.next()) {
                totalSalidas = new BigDecimal(srs.getString(RowsNames.TOTAL));
            }

            return totalSalidas;
        } catch (Exception e) {
            logger.error(e);
            throw new DAOException(ERR_GENERAL, e);
        }
    }

    @Override
    public List<CapitalDTO> getValanceMovimientos() throws DAOException {
        try {
            return getJdbcTemplateBase().query(REPORTE_MOVIMIENTOS, new CapitalMapper());
        } catch (Exception e) {
            logger.error(e);
            throw new DAOException(ERR_GENERAL, e);
        }
    }

}
