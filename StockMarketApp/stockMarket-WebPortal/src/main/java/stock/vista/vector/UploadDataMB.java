/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.util.FechaUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import stock.horizontal.dto.Vector;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Juan
 */
@Controller("uploadDataMB")
@Scope(value = "view")
public class UploadDataMB extends AbstractManagedBean {

    private List<Vector> vector;

    @PostConstruct
    public void init() {
        vector = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            Vector fondo = getFactory().manufacturePojo(Vector.class);
            fondo.setFecha(FechaUtil.formatFecha(new Date()));
            fondo.setConcepto("xxx00" + i);
            fondo.setAcciones("Emisora " + i);
            fondo.setAccionesVenta("Emisora 12" + i);
            vector.add(fondo);
        }
    }

    public void upload(FileUploadEvent event) {
        addMessage("Éxito!", event.getFile().getFileName() + " se proceso correctamente.");
    }

    public List<Vector> getVector() {
        return vector;
    }

}
