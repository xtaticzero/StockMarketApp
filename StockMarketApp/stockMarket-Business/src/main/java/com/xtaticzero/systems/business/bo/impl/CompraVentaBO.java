/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo.impl;

import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.BO;
import java.util.List;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class CompraVentaBO extends BO<CompraVentaBO> {

    private static final long serialVersionUID = -1416169287359753867L;

    private List<EmisoraDTO> lstEmisoras;
    private CapaDTO capa;

    private CompraVentaBO(UsuarioDTO usuario) {
        super();
        setUsuario(usuario);
    }

    public static CompraVentaBO getBOValido(UsuarioDTO usuario) {
        if (usuario != null && usuario.getRol_id() != null && usuario.getDisplay_name() != null) {
            return new CompraVentaBO(usuario);
        }
        return null;
    }

    public List<EmisoraDTO> getLstEmisoras() {
        return lstEmisoras;
    }

    public void setLstEmisoras(List<EmisoraDTO> lstEmisoras) {
        this.lstEmisoras = lstEmisoras;
    }

    public CapaDTO getLstCapas() {
        return capa;
    }

    public void setLstCapas(CapaDTO capas) {
        this.capa = capa;
    }

}
