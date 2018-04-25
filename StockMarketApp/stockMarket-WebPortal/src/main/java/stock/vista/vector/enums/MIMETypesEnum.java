/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stock.vista.vector.enums;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum MIMETypesEnum {
    PDF("application/pdfl"),
    EXCEL("application/vnd.ms-excel"),
    ZIP("application/octet-stream");
        
    private final String type;

    private MIMETypesEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
