/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.mapper;

import com.xtaticzero.systems.base.dto.UsuarioDTO;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class UsuarioMapper implements RowMapper<UsuarioDTO> {

    protected final String DISPLAY_NAME = "USR.display_name";
    protected final String USR_ID = "USR.user_id";
    protected final String EMAIL = "USR.email";
    protected final String FECHA = "USR.fecha";
    protected final String FECHA_TERMINO = "USR.fechaTermino";
    protected final String ROL_ID = "USR.rol_id";
    protected final String ROL = "ROL.descripcion";

    @Override
    public UsuarioDTO mapRow(ResultSet rs, int i) throws SQLException {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setDisplay_name(rs.getString(DISPLAY_NAME));
        usuario.setUser_id(new BigInteger(rs.getString(USR_ID)));
        usuario.setRol_id(new BigInteger(rs.getString(ROL_ID)));
        usuario.setRol(rs.getString(ROL));
        usuario.setEmail(rs.getString(EMAIL));
        usuario.setFecha(rs.getTimestamp(FECHA));
        usuario.setFecha(rs.getTimestamp(FECHA_TERMINO));

        return usuario;
    }

}
