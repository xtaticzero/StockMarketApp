/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.mat.test.negocio;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static mx.gob.sat.mat.tabacos.constants.Constantes.ARCHIVO_PDF;
import mx.gob.sat.mat.tabacos.constants.enums.ReportesTabacosEnum;
import mx.gob.sat.mat.tabacos.negocio.ReporterService;
import mx.gob.sat.mat.tabacos.negocio.ValidadorAccesoService;
import mx.gob.sat.mat.tabacos.negocio.ValidadorRangosService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Ing Emmanuel Estrada Gonzalez
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/**/tabacos-negocio-test.xml", "classpath*:/**/tabacos-daos-test.xml"})
//@Transactional
//@TransactionConfiguration(transactionManager = "transactionManagerTBC", defaultRollback = true)
public class TbcNegocioTest {

     @Test
    //@Ignore
    public void pruebaAcuse() throws Exception {
        try {
            assert 1-1==0;            
        } catch (Exception ex) {
            System.err.println("Error al obtener el recurso: " + ex.getCause().getMessage());
        }
    }
}
