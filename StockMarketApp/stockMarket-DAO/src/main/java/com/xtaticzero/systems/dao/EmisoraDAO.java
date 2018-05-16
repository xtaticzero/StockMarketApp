/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.DAOException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface EmisoraDAO {

    List<EmisoraDTO> findAllEmisoras() throws DAOException;
}
