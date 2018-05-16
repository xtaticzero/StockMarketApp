/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.BaseModel;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 * @param <T>
 */
public abstract class BaseRepository<T extends BaseModel> implements Serializable {

    private static final long serialVersionUID = 6534638714564131194L;
    
    /**
     * Clase de la entidad.
     */
    private Class<T> clase;

    /**
     * Constructor por defecto.
     */
    @SuppressWarnings("unchecked")
    public BaseRepository() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.clase = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }

    /**
     * Obtiene la clase.
     *
     * @return Clase
     */
    protected final Class<T> getClase() {
        return this.clase;
    }

    /**
     * Obtiene el nombre completo de la clase entidad. Incluye el nombre del
     * paquete, por ejemplo:
     * mx.gob.sat.siat.buzon.framework.domain.entity.Supuesto
     *
     * @return Nombre de la clase entidad
     */
    protected final String getEntityName() {
        return this.clase.getName();
    }

    /**
     * Metodo encargado de obtener el RowMapper dada la entidad.
     *
     * @return RowMapper de la entidad.
     * @throws com.xtaticzero.systems.base.constants.excepcion.impl.DAOException
     */
    @SuppressWarnings("unchecked")
    protected final RowMapper<T> getRowMapper() throws DAOException {

        RowMapper<T> rm = null;

        try {

            StringBuilder sb = new StringBuilder(this.clase.getPackage()
                    .getName());
            sb.append(".rowmapper.");
            sb.append(this.clase.getSimpleName());
            sb.append("RowMapper");

            String className = sb.toString();

            Class<?> claseRM = Class.forName(className);
            Constructor<?> constructor = claseRM.getConstructor();

            rm = (RowMapper<T>) constructor.newInstance();
        } catch (Exception ex) {
            throw new DAOException("",ex);
        }

        return rm;
    }

}
