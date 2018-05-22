/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.dao.util.RowsNames;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class BaseAbstractMapper implements RowsNames {

    protected final Logger logger;

    public BaseAbstractMapper() {
        logger = Logger.getLogger(getClass());
    }
}
