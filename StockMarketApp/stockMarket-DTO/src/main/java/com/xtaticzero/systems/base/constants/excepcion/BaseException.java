/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.base.constants.excepcion;

import com.xtaticzero.systems.base.constants.ExceptionConstants;
import java.text.MessageFormat;
import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public abstract class BaseException extends Exception {

    /**
     * Numero de version
     */
    private static final long serialVersionUID = 5815450130220211316L;
    protected final Logger logger = Logger.getLogger(getClass());

    /**
     * Categoria de la excepcion
     */
    private String category;
    /**
     * Situacion de la excepcion
     */
    private String situation;
    /**
     * Argumentos de la excepcion
     */
    private Object[] args;
    /**
     * Causa de la excepcion
     */
    private Throwable cause;
    /**
     * Mensaje de la excepcion
     */
    private String message;

    /**
     * Constante para una linea separadora
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Variable que guarda el nombre del paquete de datos
     */
    private static String exceptionMessagesResourceBundle;

    static {
        exceptionMessagesResourceBundle
                = ExceptionConstants.PATH_TO_RESOURCE_BUNDLE + '/' + ExceptionConstants.RESOURCE_BUNDLE_NAME;
    }

    /**
     * Constructor de la clase
     */
    protected BaseException() {
        super();
    }

    /**
     * Sobrecarga del constructor
     *
     * @param category
     * @param situation
     */
    public BaseException(final String category, final String situation) {
        this();

        this.category = category;
        this.situation = situation;
    }

    /**
     * Sobrecarga del constructor
     *
     * @param category
     * @param situation
     * @param args
     */
    public BaseException(final String category, final String situation, final Object... args) {
        this(category, situation);

        this.args = (null == args ? null : args.clone());
    }

    /**
     * Sobrecarga del constructor
     *
     * @param category
     * @param situation
     * @param cause
     */
    public BaseException(final String category, final String situation, final Throwable cause) {
        this(category, situation);

        this.cause = cause;
    }

    /**
     * Sobrecarga del constructor
     *
     * @param category
     * @param situation
     * @param cause
     * @param args
     */
    public BaseException(final String category, final String situation, final Throwable cause, final Object... args) {
        this(category, situation, args);

        this.cause = cause;
    }

    /**
     * Se obtiene la categoria
     *
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * se obtiene la situacion de la excepcion
     *
     * @return situation
     */
    public String getSituation() {
        return situation;
    }

    /**
     * Obtiene los argumentos
     *
     * @return args
     */
    public Object[] getArgs() {
        return (null == args ? null : args.clone());
    }

    /**
     *
     * @param args a fijar
     */
    protected void setArgs(final Object[] args) {
        this.args = (null == args ? null : args.clone());
    }

    /**
     * Se obtiene la causa de la excepcion
     *
     * @return cause
     */
    @Override
    public Throwable getCause() {
        return cause;
    }

    /**
     * Se obtiene el mensaje de escepcion
     *
     * @return message
     */
    @Override
    public String getMessage() {
        if (null == message) {
            message = getExceptionMessage(Locale.getDefault());
        }

        if (getArgs() != null) {
            return MessageFormat.format(message, getArgs());
        } else {
            return message;
        }
        
    }

    /**
     * Metodo para lozalizar el mensaje
     *
     * @param locale
     * @return getExceptionMessage
     */
    public String getLocalizedMessage(final Locale locale) {
        return getExceptionMessage(locale);
    }

    /**
     * Metodo para dar formato al mensaje
     * @return 
     */
    @Override
    public String toString() {
        final StringBuffer formattedMessage = new StringBuffer();

        formattedMessage.append("Class name: ").append(getClass().getName());

        if (null != getMessage() && getMessage().trim().length() > 0) {
            formattedMessage.append(LINE_SEPARATOR).append("   Message: ").append(getMessage());
        }

        if (null != getCause()) {
            formattedMessage.append(LINE_SEPARATOR).append("     Cause: ").append(getCause());
        }

        return formattedMessage.toString();
    }

    /**
     * Metodo para obtener el mensaje de error
     *
     * @param locale
     * @return exceptionMessage
     */
    private String getExceptionMessage(final Locale locale) {
        String exceptionMessage = "<Invalid exception category and/or situation>";

        ResourceBundle resources = null;

        try {
            resources = ResourceBundle.getBundle(exceptionMessagesResourceBundle, locale);
        } catch (MissingResourceException mre) {
            exceptionMessage = "<Resource bundle: [" + exceptionMessagesResourceBundle + "] does not exist>";
        } catch (NullPointerException npe) {
            // Void
        }

        if (null != resources) {

            try {
                exceptionMessage = resources.getString(getExceptionMessageKey());
            } catch (MissingResourceException mre) {
                exceptionMessage = "<No entry found for: [" + getExceptionMessageKey() + "] key>";
            } catch (NullPointerException npe) {
                // Void
            }
        }

        if (null != args) {
            try {
                exceptionMessage = String.format(exceptionMessage, args);
            } catch (IllegalFormatConversionException ifce) {
                // Void
            }
        }

        return exceptionMessage;
    }

    /**
     * Se obtiene la calve de categoria o situacion
     *
     * @return key
     */
    private String getExceptionMessageKey() {
        final StringBuffer key = new StringBuffer();

        if (null != category) {
            key.append(category);
        }

        if (null != situation) {
            key.append(".").append(situation);
        }

        return key.toString();
    }
}
