/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.DAOException;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.CapaDAO;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("capaDAO")
public class CapaDAOImpl extends BaseJDBCDao<CapaDTO> implements CapaDAO {

    private static final long serialVersionUID = -1362766772708755379L;

    @Override
    public int insert(CapaDTO nuevaCapa) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delete(CapaDTO viejaCapa) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(CapaDTO capa) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
