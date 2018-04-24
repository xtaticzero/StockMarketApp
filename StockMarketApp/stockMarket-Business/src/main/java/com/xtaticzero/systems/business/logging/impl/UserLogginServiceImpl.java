/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.logging.impl;

import com.xtaticzero.systems.business.logging.UserLogginService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("userLogginService")
public class UserLogginServiceImpl implements UserLogginService{

    @Override
    public String userName() {
        return "Admin";
    }
    
}
