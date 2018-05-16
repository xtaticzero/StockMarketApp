/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface EmisoraDAO {

    int insert(EmisoraDTO nuevaEmisora) throws DAOException;
    
    int delete(EmisoraDTO emisora) throws DAOException;
    
    int update(EmisoraDTO emisora) throws DAOException;

    List<EmisoraDTO> findAllEmisoras() throws DAOException;

}
