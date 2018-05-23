/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.preparedstatement;

import org.apache.log4j.Logger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class PreparedStatementBase {

    protected static final int PARAMETRO_INICIAL = 1;
    protected final Logger logger;

    public PreparedStatementBase() {
        logger = Logger.getLogger(getClass());
    }

}
