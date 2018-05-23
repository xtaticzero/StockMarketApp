/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.horizontal.dto;

import com.xtaticzero.systems.base.util.FechaUtil;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Juan
 */
public class Accion implements Serializable {

    private static final long serialVersionUID = -4654717909755577329L;

    private String id;
    private String fecha;
    private Integer compra;
    private Integer venta;
    private Double precioCompra;
    private Double importe;
    private Double costoUnitario;
    private Double costoTotal;
    private Double utilidad;
    private Double porcentajeVenta;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = FechaUtil.formatFecha(fecha);
    }

    public Integer getCompra() {
        return compra;
    }

    public void setCompra(Integer compra) {
        this.compra = compra;
    }

    public Integer getVenta() {
        return venta;
    }

    public void setVenta(Integer venta) {
        this.venta = venta;
    }

    public Double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(Double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public Double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(Double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Double getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(Double utilidad) {
        this.utilidad = utilidad;
    }

    public Double getPorcentajeVenta() {
        return porcentajeVenta;
    }

    public void setPorcentajeVenta(Double porcentajeVenta) {
        this.porcentajeVenta = porcentajeVenta;
    }

}
