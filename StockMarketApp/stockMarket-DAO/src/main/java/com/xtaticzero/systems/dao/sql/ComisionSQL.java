/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.sql;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface ComisionSQL {

    String TABLE_COMISION = "COMISIONES";
    String INSERT_COMISION = "INSERT INTO COMISIONES(porcentaje,descripcion)\n"
            + "VALUES(?,?)";
    String UPDATE_COMISION = "UPDATE COMISIONES SET porcentaje = ?,descripcion = ? "
            + "WHERE comision_id = ?";
    String DELETE_COMISION = "";
    String FIND_COMISION = "SELECT COM.comision_id,COM.porcentaje,COM.descripcion FROM COMISIONES COM";
}
