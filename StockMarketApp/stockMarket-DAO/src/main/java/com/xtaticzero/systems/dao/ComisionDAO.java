/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.ComisionDTO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface ComisionDAO {

    ComisionDTO insertComision(ComisionDTO newComision) throws DAOException;

    int updateComision(ComisionDTO comision) throws DAOException;

    ComisionDTO deleteComision(ComisionDTO comision) throws DAOException;

    List<ComisionDTO> findComision() throws DAOException;
}
