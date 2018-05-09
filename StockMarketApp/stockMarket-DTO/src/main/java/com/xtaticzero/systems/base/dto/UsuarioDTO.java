/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.dto;

import com.xtaticzero.systems.base.BaseModel;
import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class UsuarioDTO extends BaseModel {

    private static final long serialVersionUID = 9060326732700909763L;

    private BigInteger user_id;
    private BigInteger rol_id;
    private String rol;
    private String email;
    private String display_name;
    private String password;
    private Date fecha;
    private Date fechaTermino;

    public BigInteger getUser_id() {
        return user_id;
    }

    public void setUser_id(BigInteger user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRol_id(BigInteger rol_id) {
        this.rol_id = rol_id;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public BigInteger getRol_id() {
        return rol_id;
    }

    public void setFecha(Date fecha) {
        this.fecha = (fecha != null) ? (Date) fecha.clone() : null;
    }

    public Date getFecha() {
        return (fecha != null) ? (Date) fecha.clone() : null;
    }

    public Date getFechaTermino() {
        return  (fechaTermino != null) ? (Date) fechaTermino.clone() : null;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = (fechaTermino != null) ? (Date) fechaTermino.clone() : null;
    }

}
