/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.exception;

import com.xtaticzero.systems.base.constants.excepcion.BaseException;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
public class ReporterJasperException extends BaseException {

    private static final long serialVersionUID = 7848678833138656216L;
    private static final String CATEGORY = "jasper.exception";

    public ReporterJasperException(String situation) {
        super(CATEGORY, situation);
    }

    public ReporterJasperException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public ReporterJasperException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public ReporterJasperException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }

}
