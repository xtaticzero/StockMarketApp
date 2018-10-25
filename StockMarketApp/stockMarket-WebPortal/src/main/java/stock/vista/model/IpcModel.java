/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.model;

import com.xtaticzero.systems.base.dto.IPCDto;
import java.math.BigDecimal;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public class IpcModel extends IPCDto {

    private static final long serialVersionUID = 7991789847453972732L;

    public IpcModel(IPCDto ipc) {
        super(ipc);
    }

    public String getStyle() {
        
        if(getValorIPC()==null){
            return "";
        }

        if (getPorcentajeCotizacion().compareTo(BigDecimal.ZERO) < 0) {
            return "color:white; background-color: red; font-weight: bold;";
        } else {
            return "color:black;";
        }
    }

}
