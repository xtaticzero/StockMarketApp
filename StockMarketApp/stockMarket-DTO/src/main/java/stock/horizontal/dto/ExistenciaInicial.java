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
public class ExistenciaInicial extends FondoEmisora {

    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)
    private Double costoAdquisicion;

    public Double getCostoAdquisicion() {
        return costoAdquisicion;
    }

    public void setCostoAdquisicion(Double costoAdquisicion) {
        this.costoAdquisicion = costoAdquisicion;
    }

}
