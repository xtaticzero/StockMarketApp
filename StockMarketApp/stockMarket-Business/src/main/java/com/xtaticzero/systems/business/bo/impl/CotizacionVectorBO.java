/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.BO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CotizacionVectorBO extends BO<CotizacionVectorBO> {

    private static final long serialVersionUID = -7941723588237593411L;

    private List<CotizacionDiariaDTO> lstCotizacionesDiarias;
    private CotizacionDiariaDTO cotizacionSeleccionada;

    private CotizacionVectorBO(UsuarioDTO usuario) {
        super();
        setUsuario(usuario);
    }

    public static CotizacionVectorBO getBOValido(UsuarioDTO usuario) {
        if (usuario != null && usuario.getRol_id() != null && usuario.getDisplay_name() != null) {
            return new CotizacionVectorBO(usuario);
        }
        return null;
    }

    public List<CotizacionDiariaDTO> getLstCotizacionesDiarias() {
        return lstCotizacionesDiarias;
    }

    public void setLstCotizacionesDiarias(List<CotizacionDiariaDTO> lstCotizacionesDiarias) {
        this.lstCotizacionesDiarias = lstCotizacionesDiarias;
    }

    public CotizacionDiariaDTO getCotizacionSeleccionada() {
        return cotizacionSeleccionada;
    }

    public void setCotizacionSeleccionada(CotizacionDiariaDTO cotizacionSeleccionada) {
        this.cotizacionSeleccionada = cotizacionSeleccionada;
    }

}
