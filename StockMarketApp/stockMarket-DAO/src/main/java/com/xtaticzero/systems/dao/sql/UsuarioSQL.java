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
public interface UsuarioSQL {

    String SQL_INSERT_USER = "INSERT INTO USERS (email, display_name, password, fecha)\n"
            + "VALUES (?, ?, MD5(?), SYSDATE())";

    String USER_ID = "USR.user_id = ?";
    String DISPLAY_NAME = "USR.display_name = ?";
    String E_MAIL = "USR.email = ?";
    String ROL = "USR.rol_id = ?";

    String SQL_UPDATE_USER = "UPDATE USERS SET email = ?, display_name = ?, password = MD5(?) WHERE user_id = ?";

    String SQL_DELETE_USER = "UPDATE USERS SET fechaTermino = SYSDATE() WHERE user_id = ?";

    String SQL_FIND_HEDER = "select \n"
            + "USR.user_id,\n"
            + "USR.email,\n"
            + "USR.display_name,\n"
            + "USR.fecha,\n"
            + "USR.fechaTermino,\n"
            + "USR.rol_id,\n"
            + "ROL.descripcion\n"
            + "from USERS USR\n"
            + "inner join ROL on USR.rol_id = ROL.rol_id\n"
            + "where ";

    String SQL_FIND_BY_NAME = SQL_FIND_HEDER.concat(USER_ID);

    String SQL_FIND_BY_ROL = SQL_FIND_HEDER.concat(ROL);

    String SQL_FIND_BY_EMAIL = SQL_FIND_HEDER.concat(E_MAIL);

    String SQL_FIND_BY_ALL = SQL_FIND_HEDER.concat("1=1");

    String SQL_LOGGIN = "select \n"
            + "USR.user_id,\n"
            + "USR.email,\n"
            + "USR.display_name,\n"
            + "USR.fecha,\n"
            + "USR.fechaTermino,\n"
            + "USR.rol_id,\n"
            + "ROL.descripcion\n"
            + "from USERS USR\n"
            + "inner join ROL on USR.rol_id = ROL.rol_id\n"
            + "where \n"
            + "USR.password = MD5(?)"
            + "and \n"
            + "USR.display_name = ?\n";

}
