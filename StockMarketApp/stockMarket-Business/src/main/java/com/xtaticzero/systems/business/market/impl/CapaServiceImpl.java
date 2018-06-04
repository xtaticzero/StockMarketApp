/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.market.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.DAOException;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.market.AccionService;
import com.xtaticzero.systems.business.market.CapaService;
import com.xtaticzero.systems.dao.CapaDAO;
import com.xtaticzero.systems.dao.EmisoraDAO;
import java.math.BigInteger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Service("capaService")
public class CapaServiceImpl extends BaseBusinessServices implements CapaService {

    private static final long serialVersionUID = -5176442198687916248L;

    @Autowired
    @Qualifier("capaDAO")
    private CapaDAO capaDAO;
    @Autowired
    @Qualifier("emisoraDAO")
    private EmisoraDAO emisoraDAO;

    @Override
    public CapaDTO asignaCapa(BigInteger idEmisora) throws BusinessException {
        CapaDTO capa = null;
        try {
            BigInteger idCapa = capaDAO.existeCapaActiva(idEmisora);
            if (idCapa != null) {
                capa = capaDAO.findEmisoraById(idCapa);
                if (!capaDAO.isExistencia(idCapa)) {
                    System.out.println("### No hay existencia, se desactiva capa ");
                    capaDAO.desactivaCapa(capa);
                    System.out.println("### Se genera nueva capa ");
                    capa = generaCapa(idEmisora);
                    System.out.println("### ID_CAPA: " + capa.getCapa_id());
                }
            } else {
                capa = generaCapa(idEmisora);
            }
        } catch (DAOException ex) {
            logger.error(ex.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, ex, "No se pudo asignar una capa");
        }
        return capa;
    }

    private CapaDTO generaCapa(BigInteger idEmisora) throws BusinessException {
        CapaDTO capa = null;
        try {
            EmisoraDTO emisora = emisoraDAO.findEmisoraById(idEmisora);
            capa = new CapaDTO();
            capa.setEmisora(emisora);
            capa = capaDAO.insert(capa);
            System.out.println("### ID_CAPA: " + capa.getCapa_id());
        } catch (DAOException ex) {
            logger.error(ex.getMessage());
            throw new BusinessException(ERR_GENERAL_DESCRIPCION, ex, "No se pudo generar una capa");
        }
        return capa;
    }

}
