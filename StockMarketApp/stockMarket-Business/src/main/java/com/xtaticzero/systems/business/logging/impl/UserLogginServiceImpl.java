/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.logging.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.logging.UserLogginService;
import com.xtaticzero.systems.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("userLogginService")
public class UserLogginServiceImpl extends BaseBusinessServices implements UserLogginService {

    @Autowired
    @Qualifier("userDao")
    private UserDao userDao;

    @Override
    public UsuarioDTO logginUser(UsuarioDTO usuario) throws BusinessException{
        try {
            UsuarioDTO usr = userDao.passwordValido(usuario);
            if(usr==null){
                throw new BusinessException(ERR_LOGGIN,usuario.getDisplay_name());
            }
            return usr;
        } catch (DAOException dex) {
            throw new BusinessException(ERR_LOGGIN,dex.getCause(),dex,usuario.getDisplay_name());            
        }
    }

}
