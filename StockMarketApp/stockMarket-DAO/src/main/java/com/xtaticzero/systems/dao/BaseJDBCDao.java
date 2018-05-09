/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao;

import com.xtaticzero.systems.base.constants.excepcion.DAOException;
import com.xtaticzero.systems.base.BaseModel;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 * @param <T>
 */
public abstract class BaseJDBCDao<T extends BaseModel> extends BaseRepository<T> {
    
    protected final String ERR_GENERAL = "general";

    /**
     * Numero de version
     */
    private static final long serialVersionUID = -2424923901624965933L;

    /**
     * Log de la clase.
     */
    protected final Logger logger = Logger.getLogger(getClass());

    /**
     * Jdbc template generico.
     */
    @Autowired
    @Qualifier("jdbcTemplate")
    private transient JdbcTemplate jdbcTemplateBase;

    /**
     * Constructor por defecto.
     */
    public BaseJDBCDao() {
        super();
    }

    /**
     * Obtiene el jdbcTemplateBase.
     *
     * @return regresa el jdbcTemplateBase.
     */
    public final JdbcTemplate getJdbcTemplateBase() {
        return jdbcTemplateBase;
    }

    /**
     * Metodo encargado de realizar consultas.
     *
     * @param sql SQL de la consulta a realizar.
     * @param args Argumentos para la consulta.
     * @return Listado de objetos de la entidad.
     * @throws com.xtaticzero.systems.base.constants.excepcion.DAOException
     */
    protected final List<T> findByQuery(final String sql, final Object... args) throws DAOException {
        return this.jdbcTemplateBase.query(sql, super.getRowMapper(), args);
    }

    /**
     * Metodo encargado de relizar la consulta y obtener un solo registro.
     *
     * @param sql SQL de la consulta a realizar.
     * @param args Argumentos para la consulta.
     * @return Objeto de la entidad.
     * @throws com.xtaticzero.systems.base.constants.excepcion.DAOException
     */
    protected final T findOne(final String sql, final Object... args) throws DAOException {
        return this.jdbcTemplateBase.queryForObject(sql, super.getRowMapper(),
                args);
    }

    /**
     * Metodo encargado de guardar o actualizar en la base de datos.
     *
     * @param sql SQL de la consulta a realizar.
     * @param params Argumentos para el save or update.
     * @return 1 si se guardo o actualizo correctamente.
     */
    protected final int saveOrUpdate(final String sql, final Object[] params) {
        return this.jdbcTemplateBase.update(sql, params);
    }

    /**
     * Metodo encargado de guardar un registro en la base de datos y regresar el
     * Id del registro insertado.
     *
     * @param tableName Nombre de la tabla donde se insertara el registro
     * @param idColumn Nombre de la columna de la llave primaria, debe ser
     * numerica autoincrementable
     * @param params Mapa <nombreColumna, objValor> valores a guardar
     * @return El id generado del registro
     */
    protected final long save(final String tableName, final String idColumn,
            final Map<String, Object> params) {
        SimpleJdbcInsert simpleJdbcTemplateBase = new SimpleJdbcInsert(
                this.jdbcTemplateBase.getDataSource());

        return simpleJdbcTemplateBase.withTableName(tableName)
                .usingGeneratedKeyColumns(idColumn).executeAndReturnKey(params)
                .longValue();
    }

    /**
     * Metodo encargado de guardar un registro en la base de datos. Guarda el
     * registro sin generar el identificador, este debe ser mandado en el mapa
     * de parametros.
     *
     * @param tableName Nombre de la tabla donde se guardara el registro.
     * @param params Mapa con las relaciones columna - valor.
     * @return Regresa el numero de registros insertados.
     */
    protected final int save(final String tableName,
            final Map<String, Object> params) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(
                this.jdbcTemplateBase.getDataSource());

        return jdbcInsert.withTableName(tableName).execute(params);
    }

}
