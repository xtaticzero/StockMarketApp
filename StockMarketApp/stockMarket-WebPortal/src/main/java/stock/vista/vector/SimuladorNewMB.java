/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector;

import com.xtaticzero.systems.base.constants.excepcion.impl.BusinessException;
import com.xtaticzero.systems.base.constants.excepcion.impl.FrontException;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import stock.vista.VistaAbstractMB;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Controller("simuladorNewMB")
@Scope(value = "view")
public class SimuladorNewMB extends VistaAbstractMB {

    private static final long serialVersionUID = -7154330566109088617L;

    @PostConstruct
    public void init() throws FrontException, BusinessException {
        validateUsuarioValido();
    }

}
