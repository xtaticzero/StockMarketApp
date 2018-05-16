/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface EmisoraService {

    EmisoraDTO guardarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException;

    Boolean desactivarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException;

    Boolean actualizarEmisora(EmisoraDTO nuevaEmisora) throws BusinessException;

    List<EmisoraDTO> obtenerEmisoras() throws BusinessException;

}
