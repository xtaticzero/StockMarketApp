/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.business.market.EmisoraService;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import mx.gob.sat.mat.tabacos.vista.AbstractManagedBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    private EmisoraService emisoraService;
    private List<EmisoraDTO> emisoras;
    private BigInteger selectEmisora;

    @PostConstruct
    public void init() {
        try {
            emisoras = emisoraService.obtenerEmisoras();
        } catch (BusinessException ex) {
            getLogger().error(ex);
        }
    }

    public void agregaCompra(){
        getLogger().info("### " + selectEmisora);
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
    

}
