/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.horizontal.dto;

import java.io.Serializable;

/**
 *
 * @author Juan
 */
public class FondoEmisora implements Serializable{
    
    private String emisora;
//    @Digits(integer = 3, fraction = 0)
//    @Min(value = 001)
//    @Max(value = 999)
    private Integer acciones;
//    @Digits(integer = 3, fraction = 2)
//    @Min(value = 01)
//    @Max(value = 999)
    private Double importe;

    public String getEmisora() {
        return emisora;
    }

    public void setEmisora(String emisora) {
        this.emisora = emisora;
    }

    public Integer getAcciones() {
        return acciones;
    }

    public void setAcciones(Integer acciones) {
        this.acciones = acciones;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }
    
    
}
