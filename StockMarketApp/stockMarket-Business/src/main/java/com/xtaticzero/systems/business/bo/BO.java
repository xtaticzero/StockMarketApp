/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.bo;

import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.rules.Rule;
import java.io.Serializable;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 * @param <T>
 */
public abstract class BO<T> implements Serializable {

    private static final long serialVersionUID = -1395466513648907093L;

    private Rule<T> rule;
    private UsuarioDTO usuario;

    protected BO() {

    }

    public Rule<T> getRule() {
        return rule;
    }

    public void setRule(Rule<T> rule) {
        this.rule = rule;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

}
