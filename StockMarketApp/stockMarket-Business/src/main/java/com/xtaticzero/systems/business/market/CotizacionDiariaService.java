/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import java.math.BigInteger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface CotizacionDiariaService {

    CotizacionVectorBO getBOCotizacion(UsuarioDTO usuario) throws BusinessException;

    CotizacionVectorBO getLstCotizaciones(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException;
    
    CotizacionVectorBO actualizarCotizacion(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException;
    
    CotizacionVectorBO actualizarCotizaciones(CotizacionVectorBO cotizacionDiariaBO) throws BusinessException;
    
    CotizacionDiariaDTO findCotizacionDiariaByEmisora(BigInteger idEmisora) throws BusinessException;
    
    CotizacionDiariaDTO findCotizacionDiariaHistoricoByEmisora(BigInteger idEmisora) throws BusinessException;

}
