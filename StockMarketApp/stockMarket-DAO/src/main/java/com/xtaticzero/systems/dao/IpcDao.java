/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.IPCDto;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface IpcDao {

    IPCDto insert(IPCDto newIpc) throws DAOException;
    
    IPCDto findByYear(Date date) throws DAOException;
    
    List<IPCDto> findAll(boolean active) throws DAOException;    
    
}
