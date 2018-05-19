/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.AccionDTO;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
public interface AccionService {

    AccionDTO guardarAccion(AccionDTO nuevaAccion) throws BusinessException;
}
