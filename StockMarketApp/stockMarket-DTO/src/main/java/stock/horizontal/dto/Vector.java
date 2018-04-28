/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.horizontal.dto;

import java.util.Date;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Juan
 */
public class Vector {

    private String fecha;
    private String concepto;
    @Digits(integer = 3, fraction = 0)
    @Min(value = 01)
    @Max(value = 999)
    private Double titulos;
    @Digits(integer = 3, fraction = 0)
    @Min(value = 01)
    @Max(value = 999)
    private String acciones;
    @Digits(integer = 3, fraction = 0)
    @Min(value = 01)
    @Max(value = 999)
    private Double titulosVenta;
    private String accionesVenta;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)
    private Double bruto;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)
    private Double neto;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Double getTitulos() {
        return titulos;
    }

    public void setTitulos(Double titulos) {
        this.titulos = titulos;
    }

    public String getAcciones() {
        return acciones;
    }

    public void setAcciones(String acciones) {
        this.acciones = acciones;
    }

    public Double getTitulosVenta() {
        return titulosVenta;
    }

    public void setTitulosVenta(Double titulosVenta) {
        this.titulosVenta = titulosVenta;
    }

    public String getAccionesVenta() {
        return accionesVenta;
    }

    public void setAccionesVenta(String accionesVenta) {
        this.accionesVenta = accionesVenta;
    }

    public Double getBruto() {
        return bruto;
    }

    public void setBruto(Double bruto) {
        this.bruto = bruto;
    }

    public Double getNeto() {
        return neto;
    }

    public void setNeto(Double neto) {
        this.neto = neto;
    }

}
