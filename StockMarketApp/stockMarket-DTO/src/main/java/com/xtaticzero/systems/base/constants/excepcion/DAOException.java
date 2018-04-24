/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.constants.excepcion;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class DAOException extends BaseException {

    private static final long serialVersionUID = 7630008184099485121L;
    private static final String CATEGORY = "dao.exception";

    public DAOException(String situation) {
        super(CATEGORY, situation);
    }

    public DAOException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public DAOException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public DAOException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
