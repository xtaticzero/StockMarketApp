/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.horizontal.dto.Accion;
import stock.horizontal.dto.Capa;

/**
 *
 * @author Juan
 */
@Controller("simuladorMB")
@Scope(value = "view")
public class SimuladorMB extends AbstractManagedBean {

    private static final long serialVersionUID = -4699717909755577329L;

    private List<Capa> capas;
    private List<Capa> selectedCapas;

    @PostConstruct
    public void init() {
        capas = new ArrayList<>();
        List<Accion> acciones = new ArrayList<>();

        Accion accion1 = new Accion();
        accion1.setId("CEMEX_1_1");
        accion1.setFecha(new Date());
        accion1.setCompra(5);
        accion1.setVenta(0);
        accion1.setPrecioCompra(10.00);
        accion1.setImporte(accion1.getCompra() * accion1.getPrecioCompra());
        accion1.setCostoUnitario(9.80);
        accion1.setCostoTotal(accion1.getCompra() * accion1.getCostoUnitario());
        accion1.setUtilidad(accion1.getImporte() - accion1.getCostoTotal());
        accion1.setPorcentajeVenta(accion1.getUtilidad() / accion1.getImporte());

        acciones.add(accion1);

        Capa capa1 = new Capa();
        capa1.setId("CEMEX_1_1");
        capa1.setAcciones(acciones);

        Capa capa2 = new Capa();
        capa2.setId("CEMEX_2_1");
        capa2.setAcciones(acciones);

        capas.add(capa1);
        capas.add(capa2);

    }

    public List<Capa> getCapas() {
        return capas;
    }

    public void setCapas(List<Capa> capas) {
        this.capas = capas;
    }

    public List<Capa> getSelectedCapas() {
        return selectedCapas;
    }

    public void setSelectedCapas(List<Capa> selectedCapas) {
        this.selectedCapas = selectedCapas;
    }

}
