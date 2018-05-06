/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.dao.impl;

import com.xtaticzero.systems.base.dto.BaseModel;
import com.xtaticzero.systems.dao.BaseJDBCDao;
import com.xtaticzero.systems.dao.PruebaDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Repository("pruebaDao")
public class PruebaDaoImpl extends BaseJDBCDao<BaseModel> implements PruebaDao {

    private static final long serialVersionUID = 4522424596591392227L;

    @Override
    public String getTime() {
        return getJdbcTemplateBase().queryForObject("SELECT DATE_FORMAT(SYSDATE(), 'Emmanuel %Y-%m-%d')", String.class);        
    }

}
