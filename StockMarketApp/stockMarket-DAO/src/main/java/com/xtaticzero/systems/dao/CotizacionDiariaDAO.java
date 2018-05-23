/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CotizacionDiariaDAO {

    CotizacionDiariaDTO insert(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;

    CotizacionDiariaDTO update(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;

    CotizacionDiariaDTO delete(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;
    
    CotizacionDiariaDTO findCotizacionDiariaByEmisora(EmisoraDTO emisora) throws DAOException;
    
    CotizacionDiariaDTO findAllCotizacionDiaria() throws DAOException;
}
