/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.test.impl;

import com.xtaticzero.systems.base.constants.excepcion.BusinessException;
import com.xtaticzero.systems.base.enums.ReportsEnum;
import com.xtaticzero.systems.business.logging.UserLogginService;
import com.xtaticzero.systems.business.util.ReporterService;
import com.xtaticzero.systems.test.base.BaseTest;
import java.io.File;
import java.io.FileOutputStream;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class ReporteTest extends BaseTest {

    @Autowired
    @Qualifier("userLogginService")
    private UserLogginService userService;

    @Autowired
    @Qualifier("reporterService")
    private ReporterService reporterService;
    
    @Before
    public void init(){
        File files = new File("/WebApps/tmp");
        if (!files.exists()) {
            if (files.mkdirs()) {
                System.out.println("Multiple directories are created!");
            } else {
                System.out.println("Failed to create multiple directories!");
            }
        }
    }

    @Test
    public void pruebaReportePDF() throws Exception {
        try {
            if (userService != null && reporterService != null) {
                logger.info(userService.userName());

                byte[] byt = reporterService.makeReport(ReportsEnum.REPORTE_PRUEBA, "prueba.pdf", null, null);

                try (FileOutputStream fos = new FileOutputStream("/WebApps/tmp/reporte.pdf")) {
                    fos.write(byt);
                    //fos.close(); There is no more need for this line since you had created the instance of "fos" inside the try. And this will automatically close the OutputStream
                } catch (Exception ex) {
                    logger.error("Error al crear archivo",ex);
                }

                throw new BusinessException("general", "prueba", "01");
            } else {
                logger.error("userService is null");
            }

            int suma = 3 + 5;
            logger.info(suma);
        } catch (BusinessException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
