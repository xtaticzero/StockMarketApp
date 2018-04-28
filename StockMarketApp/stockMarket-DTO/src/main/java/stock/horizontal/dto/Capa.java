/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.horizontal.dto;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Juan
 */
public class Capa implements Serializable{
    
        private static final long serialVersionUID = -4654717909755977329L;


    private String id;
    private List<Accion> acciones;
    private Double costo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

}
