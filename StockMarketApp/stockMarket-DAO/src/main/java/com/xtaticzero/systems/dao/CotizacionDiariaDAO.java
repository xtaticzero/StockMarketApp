/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.CotizacionHistoricoDTO;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CotizacionDiariaDAO {

    CotizacionDiariaDTO insert(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;

    int update(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;

    int delete(CotizacionDiariaDTO cotizacionDiaria) throws DAOException;

    CotizacionDiariaDTO findCotizacionDiariaByEmisora(BigInteger idEmisora) throws DAOException;

    List<CotizacionHistoricoDTO> findCotizacionHistoryByEmisoraCurrentYear(BigInteger idEmisora) throws DAOException;

    List<CotizacionHistoricoDTO> findCotizacionHistoryByEmisoraFilter(BigInteger idEmisora, Date year) throws DAOException;

    List<CotizacionDiariaDTO> findAllCotizacionDiaria() throws DAOException;

    int[] updateBatch(List<CotizacionDiariaDTO> lstCotizaciones) throws DAOException;
    
    int[] updateFromExcel(List<CotizacionDiariaDTO> lstCotizaciones) throws DAOException;

    CotizacionHistoricoDTO findLastCotizacionHistoricaByDate(BigInteger idEmisora, Integer yearFiltro) throws DAOException;
    
    List<Integer> findAllYearsHistory() throws DAOException;
}
