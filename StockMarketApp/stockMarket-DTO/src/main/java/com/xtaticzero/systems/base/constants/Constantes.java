/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.constants;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface Constantes {

    //Tamaño minimo de RFC
    int RFC_LENGTH_MIN = 10;

    /**
     * Valor que significa si el contribuyente existe en el PCBA.
     */
    int EXISTE_EN_PCBA = 1;

    /**
     * Atributo que representa el c&oacute;digo para el pa&iacute;s
     * M&eacute;xico
     */
    String CODIGO_PAIS_MEXICO = "MEX";

    /**
     * Atributo que representa la longitud que debe de tener un rfc de persona
     * moral
     */
    Integer LONGITUD_RFC_PERSONA_MORAL = 12;

    /**
     * Atributo que representa la longitud que debe de tener un rfc de persona
     * fisica
     */
    Integer LONGITUD_RFC_PERSONA_FISICA = 13;

    /**
     * Atributo que representa los milisegundos de un d&iacute;a
     */
    long MILISEGUNDOS_POR_DIA = 24 * 60 * 60 * 1000;

    /**
     * Atributeo que representa salto de linea para windows
     */
    String SALTO_LINEA = "\r\n";

    /**
     * Atributo que representa un espacio vacio.
     */
    String ESPACIO_VACIO = "";

    /**
     * Postfijo de archivos excel con el formato mayor a 2007.
     */
    String EXCEL_DESPUES_2007 = ".xlsx";

    /**
     * Postfijo de archivos excel con el formato de 2007 o inferior.
     */
    String EXCEL_ANTES_2007 = ".xls";

    /**
     * Postfijo de archivos csv con el formato de 2007 o inferior.
     */
    String ARCHIVO_CSV = ".csv";

    /**
     * Extension de archivos word.
     */
    String WORD_2003 = ".doc";

    String WORD_2007 = ".docx";

    /**
     * Postfijo de archivos pdf.
     */
    String ARCHIVO_PDF = ".pdf";

    /**
     * Postfijo de archivos png.
     */
    String ARCHIVO_PNG = "png";

    /**
     * Postfijo de archivos cer.
     */
    String POSTFIJO_ARCHIVO_CER = ".cer";

    /**
     * Postfijo de archivos key.
     */
    String POSTFIJO_ARCHIVO_KEY = ".key";

    /**
     * Postfijo de archivos txt
     */
    String ARCHIVO_TXT = ".txt";

    /**
     * Extencion de archivos jasper.
     */
    String TERMINACION_ARCHIVO_JASPER = ".jasper";

    String EXP_REG_EMAIL = "^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,4})*$";

    /**
     * Directorio donde se alojan los archivos que carga el contribuyente.
     */
    String PAGINA_ADD_ENCONSTRUCCION_ERROR = "/pages/enConstruccionError/enConstruccionError.jsf";

}
