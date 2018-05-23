/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.CotizacionDiariaDAO;
import com.xtaticzero.systems.dao.sql.CotizacionDiariaSQL;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionDiariaDAOImpl extends BaseJDBCDao<CotizacionDiariaDTO> implements CotizacionDiariaDAO, CotizacionDiariaSQL {

    private static final long serialVersionUID = -7960526923843331731L;

    @Override
    public CotizacionDiariaDTO insert(CotizacionDiariaDTO cotizacionDiaria) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CotizacionDiariaDTO update(CotizacionDiariaDTO cotizacionDiaria) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CotizacionDiariaDTO delete(CotizacionDiariaDTO cotizacionDiaria) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CotizacionDiariaDTO findCotizacionDiariaByEmisora(EmisoraDTO emisora) throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public CotizacionDiariaDTO findAllCotizacionDiaria() throws DAOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
