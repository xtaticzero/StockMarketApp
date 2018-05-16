/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.DAOException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.EmisoraDAO;
import com.xtaticzero.systems.dao.mapper.EmisoraMapper;
import com.xtaticzero.systems.dao.sql.EmisorasSQL;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("emisoraDAO")
public class EmisoraDaoImpl extends BaseJDBCDao<EmisoraDTO> implements EmisoraDAO,EmisorasSQL {

    private static final long serialVersionUID = 1124202868363386253L;

    @Override
    public List<EmisoraDTO> findAllEmisoras() throws DAOException {
        try {
            return getJdbcTemplateBase().query(SELECT_ALL_EMISORAS, new EmisoraMapper());
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            throw new DAOException(ERR_GENERAL,e.getMessage(),e);
        }
    }
    
}
