package stock.vista.vector;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.market.dto.Fondo;

/**
 *
 * @author Alejandro Hernandez
 */
@Controller("estadoMensualMB")
@Scope(value = "view")

public class EstadoMensualMB extends AbstractManagedBean {

    private List<Fondo> fondos;

    @PostConstruct
    public void init() {
        fondos = new ArrayList<>();                
        for (int i = 1; i <= 9; i++) {
            Fondo fondo = getFactory().manufacturePojo(Fondo.class);
            fondos.add(fondo);
        }

    }

    public List<Fondo> getFondos() {
        return fondos;
    }

}
