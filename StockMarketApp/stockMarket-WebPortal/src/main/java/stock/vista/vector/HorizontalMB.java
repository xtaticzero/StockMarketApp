/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.horizontal.dto.ExistenciaInicial;
import stock.horizontal.dto.Utilidad;

/**
 *
 * @author Juan
 */
@Controller("horizontalMB")
@Scope(value = "view")
public class HorizontalMB extends AbstractManagedBean {

    private DecimalFormat df = new DecimalFormat("#.00");
    private List<ExistenciaInicial> existencias;
    private List<Utilidad> utilidades;

    @PostConstruct
    public void init() {
        existencias = new ArrayList<>();
        utilidades = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            ExistenciaInicial existencia = getFactory().manufacturePojo(ExistenciaInicial.class);
            existencia.setEmisora("Emisora " + i);
            existencias.add(existencia);
        }

        for (int i = 1; i <= 9; i++) {
            Utilidad utilidad = getFactory().manufacturePojo(Utilidad.class);
            utilidad.setEmisora("Emisora " + i);
            utilidades.add(utilidad);
        }

    }

    public Integer getSumaAccionesExistencias() {
        int suma = 0;
        for (ExistenciaInicial exis : existencias) {
            suma += exis.getAcciones();
        }
        return suma;
    }

    public String getSumaImportesExistencias() {
        double suma = 0.0;
        for (ExistenciaInicial exis : existencias) {
            suma += exis.getImporte();
        }
        return df.format(suma);
    }

    public Integer getSumaAccionesUtilidades() {
        int suma = 0;
        for (Utilidad util : utilidades) {
            suma += util.getAcciones();
        }
        return suma;
    }

    public String getSumaImportesUtilidades() {
        double suma = 0.0;
        for (Utilidad util : utilidades) {
            suma += util.getImporte();
        }
        return df.format(suma);
    }

    public List<ExistenciaInicial> getExistencias() {
        return existencias;
    }

    public List<Utilidad> getUtilidades() {
        return utilidades;
    }

}
