/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.impl;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.base.dto.UsuarioDTO;
import com.xtaticzero.systems.business.bo.impl.CotizacionVectorBO;
import com.xtaticzero.systems.business.market.CotizacionDiariaService;
import com.xtaticzero.systems.business.util.ExcelReaderService;
import com.xtaticzero.systems.business.util.TipoArchivoCargaEnum;
import com.xtaticzero.systems.test.base.BaseTest;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class ExcelReaderTest extends BaseTest {

    @Autowired
    @Qualifier("excelReaderService")
    private ExcelReaderService excelReaderService;

    @Autowired
    @Qualifier("cotizacionDiariaService")
    private CotizacionDiariaService cotizacionService;

    private File file;
    private UsuarioDTO usr;
    private CotizacionVectorBO cotizacionBO;
    private List<IPCDto> lstIpc;
    public static final String SAMPLE_XLSX_FILE_PATH = "/Users/xtati/Documents/CargaIPC.xlsx";
    public static final String SAMPLE_COTIZACION_XLSX_FILE_PATH = "/Users/xtati/Documents/CargaCotizacionDiariaPlantilla.xlsx";

    @Before
    public void init() {
        try {
            file = new File(SAMPLE_XLSX_FILE_PATH);
            usr = new UsuarioDTO();

            usr.setDisplay_name("admin");
            usr.setPassword("admin123");

            usr = userService.logginUser(usr);

            cotizacionBO = cotizacionService.getBOCotizacion(usr);
        } catch (BusinessException ex) {
            logger.error(ex);
        }
    }

    @Test
    @Ignore
    public void loadIPC() {
        try {
            cotizacionService.insertIPCFromExcel(cotizacionBO, file, null);
        } catch (Exception e) {
            logger.error(e);
        }
    }
    @Test
    public void loadExcelCotizacionDiaria() {
        try {
            List<CotizacionDiariaDTO> lst;
            lst = (List<CotizacionDiariaDTO>)excelReaderService.getLstCargaByType(new FileInputStream(SAMPLE_COTIZACION_XLSX_FILE_PATH), TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA);
            cotizacionService.getLstCotizacionesFromExcel(cotizacionBO, new FileInputStream(SAMPLE_COTIZACION_XLSX_FILE_PATH));
            cotizacionService.insertLstCotizacionesFromExcel(cotizacionBO);
            
            
            if(lst!=null){
                for (CotizacionDiariaDTO cotizacionDiariaDTO : lst) {
                    logger.debug(cotizacionDiariaDTO);
                }
            }
        
        } catch (Exception e) {
            logger.error(e);
        }
    }
}
