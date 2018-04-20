/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

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

   
    public void upload(FileUploadEvent event) {
            addMessage("Éxito!", event.getFile().getFileName() + " se proceso correctamente.");   
    }

   
}
