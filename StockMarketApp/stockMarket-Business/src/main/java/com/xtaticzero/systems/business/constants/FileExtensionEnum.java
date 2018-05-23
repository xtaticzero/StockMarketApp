/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.constants;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public enum FileExtensionEnum {
    PDF(".pdf"),
    EXCEL(".xls"),
    ZIP(".zip"),
    WITHOUT("");

    private final String extension;

    FileExtensionEnum(String extension) {
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }
}
