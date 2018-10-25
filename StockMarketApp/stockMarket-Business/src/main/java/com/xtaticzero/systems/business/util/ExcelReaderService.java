/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util;

import com.xtaticzero.systems.base.BaseModel;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
public interface ExcelReaderService {

    List<? extends BaseModel> getLstOfIpc(File file, TipoArchivoCargaEnum tipoArchivo) throws IOException, InvalidFormatException;

    List<? extends BaseModel> getLstOfIpc(InputStream file, TipoArchivoCargaEnum tipoArchivo) throws IOException, InvalidFormatException;
}
