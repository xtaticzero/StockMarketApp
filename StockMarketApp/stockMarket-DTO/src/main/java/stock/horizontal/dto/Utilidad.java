/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.horizontal.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Juan
 */
public class Utilidad extends FondoEmisora{
    
    @Digits(integer = 3, fraction = 0)
    @Min(value = 01)
    @Max(value = 999)
    private Integer utilidadXAccion;

    public Integer getUtilidadXAccion() {
        return utilidadXAccion;
    }

    public void setUtilidadXAccion(Integer utilidadXAccion) {
        this.utilidadXAccion = utilidadXAccion;
    }
    
}
