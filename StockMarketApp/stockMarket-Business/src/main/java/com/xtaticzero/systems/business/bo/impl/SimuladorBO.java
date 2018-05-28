/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.ComisionDTO;
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

    private List<CapaDTO> lstCapas;
    private List<CapaDTO> lstCapasSeleccionadas;

    public SimuladorBO getBOValido(UsuarioDTO usuario) {
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

    public List<CapaDTO> getLstCapas() {
        return lstCapas;
    }

    public void setLstCapas(List<CapaDTO> lstCapas) {
        this.lstCapas = lstCapas;
    }

    public List<CapaDTO> getLstCapasSeleccionadas() {
        return lstCapasSeleccionadas;
    }

    public void setLstCapasSeleccionadas(List<CapaDTO> lstCapasSeleccionadas) {
        this.lstCapasSeleccionadas = lstCapasSeleccionadas;
    }
    
    
}
