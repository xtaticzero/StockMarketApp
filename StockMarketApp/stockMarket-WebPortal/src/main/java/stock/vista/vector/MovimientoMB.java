/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.AccionDTO;
import com.xtaticzero.systems.base.dto.CapaAccionDTO;
import com.xtaticzero.systems.base.dto.CapaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.business.market.AccionService;
import com.xtaticzero.systems.business.market.CapaAccionService;
import com.xtaticzero.systems.business.market.CapaService;
import com.xtaticzero.systems.business.market.EmisoraService;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Juan Antonio Perez Ramos | dev.juan.perez@gmail.com
 */
@Controller("movimientoMB")
@Scope(value = "view")
public class MovimientoMB extends AbstractManagedBean {

    @Autowired
    @Qualifier("emisoraService")
    private EmisoraService emisoraService;
    @Autowired
    @Qualifier("capaService")
    private CapaService capaService;
    @Autowired
    @Qualifier("accionService")
    private AccionService accionService;
    @Autowired
    @Qualifier("capaAccionService")
    private CapaAccionService capaAccionService;

    private List<EmisoraDTO> emisoras;
    private BigInteger selectEmisora;
    private AccionDTO accion;

    @PostConstruct
    public void init() {
        try {
            emisoras = emisoraService.obtenerEmisoras();
            accion = new AccionDTO();
        } catch (BusinessException ex) {
            getLogger().error(ex);
        }
    }

    public void agregaCompra() {
        try {
            getLogger().info("### Asignar Capa " + selectEmisora);
            CapaDTO capa = capaService.asignaCapa(selectEmisora);
            accion = accionService.guardarAccion(accion);

            CapaAccionDTO relacion = new CapaAccionDTO();
            relacion.setAccion(accion);
            relacion.setCapa(capa);

            capaAccionService.guardarAccion(relacion);

        } catch (BusinessException ex) {
            getLogger().error(ex);
        }

    }

    /**
     * getters and setters
     */
    public List<EmisoraDTO> getEmisoras() {
        return emisoras;
    }

    public BigInteger getSelectEmisora() {
        return selectEmisora;
    }

    public void setSelectEmisora(BigInteger selectEmisora) {
        this.selectEmisora = selectEmisora;
    }

    public AccionDTO getAccion() {
        return accion;
    }

    public void setAccion(AccionDTO accion) {
        this.accion = accion;
    }

}
