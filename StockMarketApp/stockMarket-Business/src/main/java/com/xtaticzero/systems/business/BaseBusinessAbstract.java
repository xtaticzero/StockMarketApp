/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business;

import java.io.Serializable;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class BaseBusinessAbstract implements Serializable {

    private static final long serialVersionUID = -7658743465565989215L;
    protected final Logger logger;

    public BaseBusinessAbstract() {
        logger = Logger.getLogger(getClass());
    }
}
