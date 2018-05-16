/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapaDTO;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CapaDAO {

    int insert(CapaDTO nuevaCapa) throws DAOException;

    int delete(CapaDTO viejaCapa) throws DAOException;

    int update(CapaDTO capa) throws DAOException;
}
