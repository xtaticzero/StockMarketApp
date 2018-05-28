/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapitalDTO;
import com.xtaticzero.systems.dao.sql.CapitalSQL;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CapitalDAO extends CapitalSQL {

    CapitalDTO agregarEntrada(CapitalDTO entradaCapital) throws DAOException;

    CapitalDTO agregarSalida(CapitalDTO salidaCapital) throws DAOException;

    BigDecimal totalEntradas() throws DAOException;

    BigDecimal totalSalidas() throws DAOException;
    
    List<CapitalDTO> getValanceMovimientos() throws DAOException;
}
