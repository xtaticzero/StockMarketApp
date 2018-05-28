/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.ComisionDTO;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.BO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class SimuladorBO extends BO<SimuladorBO> {

    private static final long serialVersionUID = -80067601073223573L;

    private SimuladorBO(UsuarioDTO usuario) {
        super(usuario);
    }

    private ComisionDTO comision;
    private CotizacionVectorBO cotizacionDiariaBo;

    private List<CapaAccionDTO> lstCapaAccion;
    private List<CapaAccionDTO> lstCapaAccionSeleccionadas;

    public static SimuladorBO getBOValido(UsuarioDTO usuario) {
        if (usuario != null && usuario.getRol_id() != null && usuario.getDisplay_name() != null) {
            return new SimuladorBO(usuario);
        }
        return null;
    }

    public ComisionDTO getComision() {
        return comision;
    }

    public void setComision(ComisionDTO comision) {
        this.comision = comision;
    }

    public List<CapaAccionDTO> getLstCapaAccion() {
        return lstCapaAccion;
    }

    public void setLstCapaAccion(List<CapaAccionDTO> lstCapaAccion) {
        this.lstCapaAccion = lstCapaAccion;
    }

    public List<CapaAccionDTO> getLstCapaAccionSeleccionadas() {
        return lstCapaAccionSeleccionadas;
    }

    public void setLstCapaAccionSeleccionadas(List<CapaAccionDTO> lstCapaAccionSeleccionadas) {
        this.lstCapaAccionSeleccionadas = lstCapaAccionSeleccionadas;
    }

    public CotizacionVectorBO getCotizacionDiariaBo() {
        return cotizacionDiariaBo;
    }

    public void setCotizacionDiariaBo(CotizacionVectorBO cotizacionDiariaBo) {
        this.cotizacionDiariaBo = cotizacionDiariaBo;
    }

}
