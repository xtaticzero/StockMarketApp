/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface ActualizardorCotizacionesBOService {

    boolean actualizarCotizacionesAlDia() throws BusinessException;
}
