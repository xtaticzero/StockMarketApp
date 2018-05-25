/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.constans;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum CatalogoErroresEnum {
    ERROR_USUARIO_INVALIDO("VISTA", "V001"),
    ERROR_VISTA_COMPONENTES("VISTA", "V002");

    private final String tipo;
    private final String codigo;

    private CatalogoErroresEnum(String tipo, String codigo) {
        this.tipo = tipo;
        this.codigo = codigo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCodigo() {
        return codigo;
    }

}
