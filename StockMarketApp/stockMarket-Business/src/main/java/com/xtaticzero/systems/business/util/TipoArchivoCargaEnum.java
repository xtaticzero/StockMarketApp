/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum TipoArchivoCargaEnum {
    ARCHIVO_CARGA_IPC(1,"Archivo de carga IPC");
    private final String descTipo;
    private final int idTipo;

    private TipoArchivoCargaEnum(int idTipo, String descTipo) {
        this.descTipo = descTipo;
        this.idTipo = idTipo;
    }

    public String getDescTipo() {
        return descTipo;
    }

    public int getIdTipo() {
        return idTipo;
    }
    
}
