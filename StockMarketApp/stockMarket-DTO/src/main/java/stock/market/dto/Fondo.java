/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.market.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 *
 * @author Alejandro Hernandez
 */
public class Fondo {

    //Capital Inicial
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)

    private Double efectivo;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)

    private Double accionesCostoAdquisicion;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)

    private Double utilidadPerdidaRealizar;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)

    private Double accionesValorMercado;
    @Digits(integer = 3, fraction = 2)
    @Min(value = 01)
    @Max(value = 999)

    private Double capitalInicial;

    public Double getEfectivo() {
        return efectivo;
    }

    public void setEfectivo(Double efectivo) {
        this.efectivo = efectivo;
    }

    public Double getAccionesCostoAdquisicion() {
        return accionesCostoAdquisicion;
    }

    public void setAccionesCostoAdquisicion(Double accionesCostoAdquisicion) {
        this.accionesCostoAdquisicion = accionesCostoAdquisicion;
    }

    public Double getUtilidadPerdidaRealizar() {
        return utilidadPerdidaRealizar;
    }

    public void setUtilidadPerdidaRealizar(Double utilidadPerdidaRealizar) {
        this.utilidadPerdidaRealizar = utilidadPerdidaRealizar;
    }

    public Double getAccionesValorMercado() {
        return accionesValorMercado;
    }

    public void setAccionesValorMercado(Double accionesValorMercado) {
        this.accionesValorMercado = accionesValorMercado;
    }

    public Double getCapitalInicial() {
        return capitalInicial;
    }

    public void setCapitalInicial(Double capitalInicial) {
        this.capitalInicial = capitalInicial;
    }

}
