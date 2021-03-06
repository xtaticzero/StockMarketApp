/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util.impl;

import com.xtaticzero.systems.base.BaseModel;
import com.xtaticzero.systems.base.dto.CotizacionDiariaDTO;
import com.xtaticzero.systems.base.dto.EmisoraDTO;
import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.util.ExcelConstant;
import com.xtaticzero.systems.business.util.ExcelReaderService;
import com.xtaticzero.systems.business.util.TipoArchivoCargaEnum;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez <emmanuel.estradag.ipn@gmail.com>
 */
@Service("excelReaderService")
public class ExcelReaderServiceImpl extends BaseBusinessServices implements ExcelReaderService {

    private static final long serialVersionUID = -8058095622316208810L;

    private Workbook init(File file) throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        return WorkbookFactory.create(file);

    }

    private Workbook init(InputStream inputStream) throws IOException, InvalidFormatException {
        // Creating a Workbook from an Excel file (.xls or .xlsx)
        return WorkbookFactory.create(inputStream);

    }

    @Override
    public List<? extends BaseModel> getLstCargaByType(File file, TipoArchivoCargaEnum tipoArchivo) throws IOException, InvalidFormatException {
        return process(init(file), tipoArchivo);
    }

    @Override
    public List<? extends BaseModel> getLstCargaByType(InputStream file, TipoArchivoCargaEnum tipoArchivo) throws IOException, InvalidFormatException {
        return process(init(file), tipoArchivo);
    }

    private List<? extends BaseModel> process(Workbook wb, TipoArchivoCargaEnum tipoArchivo) {
        List<IPCDto> lstIpc = new ArrayList<>();
        List<CotizacionDiariaDTO> lstCotizacionDiaria = new ArrayList<>();
        BaseModel objCarga = null;

        Sheet sheet = wb.getSheetAt(0);
        FormulaEvaluator evaluator = wb.getCreationHelper().createFormulaEvaluator();

        // 1. You can obtain a rowIterator and columnIterator and iterate over them
        logger.debug("\n\nIterating over Rows and Columns using Iterator\n");
        Iterator<Row> rowIterator = sheet.rowIterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue;
            }

            if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
                objCarga = new IPCDto();
            }

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();
            Date fechaCarga = null;
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                    objCarga = new CotizacionDiariaDTO();
                }
                if (evaluator.evaluateFormulaCell(cell) != Cell.CELL_TYPE_BLANK) {
                    if (cell != null && objCarga != null) {
                        logger.debug("cell.getColumnIndex()" + cell.getColumnIndex());

                        switch (cell.getColumnIndex()) {
                            case ExcelConstant.COLUM_0:
                                logger.debug(cell.getDateCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
                                    ((IPCDto) objCarga).setDiaMovimiento(cell.getDateCellValue());
                                }
                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    fechaCarga = (cell.getDateCellValue());
                                }

                                break;
                            case ExcelConstant.COLUM_1:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
                                    ((IPCDto) objCarga).setValorIPC(new BigDecimal(cell.getNumericCellValue()));
                                }

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_1)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_2:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
                                    ((IPCDto) objCarga).setPorcentajeCotizacion(new BigDecimal(cell.getNumericCellValue()));
                                }

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_2)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_3:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_3)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_4:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_4)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_5:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_5)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_6:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_6)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_7:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_7)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_8:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_8)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            case ExcelConstant.COLUM_9:
                                logger.debug(cell.getNumericCellValue());

                                if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                                    ((CotizacionDiariaDTO) objCarga).setEmisora(new EmisoraDTO());
                                    ((CotizacionDiariaDTO) objCarga).getEmisora().setEmisora_id(new BigInteger(String.valueOf(ExcelConstant.COLUM_9)));
                                    ((CotizacionDiariaDTO) objCarga).setCostoAlDia(new BigDecimal(cell.getNumericCellValue()));
                                }

                                break;
                            default:
                                break;
                        }
                        if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
                            ((CotizacionDiariaDTO) objCarga).setDiaCotizacion(fechaCarga);
                            if (((CotizacionDiariaDTO) objCarga).getEmisora() != null && ((CotizacionDiariaDTO) objCarga).getEmisora().getEmisora_id() != null && !(((CotizacionDiariaDTO) objCarga).getCostoAlDia().equals(BigDecimal.ZERO)) ) {
                                lstCotizacionDiaria.add(((CotizacionDiariaDTO) objCarga));
                            }
                        }
                    }
                }

            }

            if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
                if (((IPCDto) objCarga).getValorIPC() != null) {
                    lstIpc.add(((IPCDto) objCarga));
                }
            }

        }

        if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_IPC)) {
            return lstIpc;
        }
        if (tipoArchivo.equals(TipoArchivoCargaEnum.ARCHIVO_CARGA_COTIZACION_DIARIA)) {
            return lstCotizacionDiaria;
        } else {
            return new ArrayList<BaseModel>();
        }
    }

}
