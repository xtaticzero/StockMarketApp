/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.util;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class StringUtil {
    // Se oculta constructor default
    private StringUtil() { }
    
    /**
     * M&eacute;todo que valida que un string sea vacio o nulo.
     * @param cadena a validar.
     * @return true si la cadena es nula o vacia, de lo contrario false.
     */
    public static boolean isEmpty(String cadena){
        return (cadena == null || cadena.isEmpty());
    }
}
