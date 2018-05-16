/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.constants.excepcion.impl;

import com.xtaticzero.systems.base.constants.excepcion.BaseException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class FrontException extends BaseException {

    private static final long serialVersionUID = -2581674290294658652L;
    private static final String CATEGORY = "front.exception";

    public FrontException(String situation) {
        super(CATEGORY, situation);
    }

    public FrontException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public FrontException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public FrontException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
