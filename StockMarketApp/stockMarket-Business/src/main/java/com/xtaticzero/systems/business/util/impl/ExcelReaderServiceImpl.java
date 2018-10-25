/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.xtaticzero.systems.business.util.impl;

import com.xtaticzero.systems.base.dto.IPCDto;
import com.xtaticzero.systems.business.BaseBusinessServices;
import com.xtaticzero.systems.business.util.ExcelConstant;
import com.xtaticzero.systems.business.util.ExcelReaderService;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    public List<IPCDto> getLstOfIpc(File file) throws IOException, InvalidFormatException {
        return process(init(file));
    }

    @Override
    public List<IPCDto> getLstOfIpc(InputStream file) throws IOException, InvalidFormatException {
        return process(init(file));
    }

    private List<IPCDto> process(Workbook wb) {
        List<IPCDto> lstIpc = new ArrayList<>();
        IPCDto ipc;

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

            ipc = new IPCDto();

            // Now let's iterate over the columns of the current row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                if (evaluator.evaluateFormulaCell(cell) != Cell.CELL_TYPE_BLANK) {
                    if (cell != null) {
                        logger.debug("cell.getColumnIndex()" + cell.getColumnIndex());

                        switch (cell.getColumnIndex()) {
                            case ExcelConstant.COLUM_0:
                                logger.debug(cell.getDateCellValue());
                                ipc.setDiaMovimiento(cell.getDateCellValue());
                                break;
                            case ExcelConstant.COLUM_1:
                                logger.debug(cell.getNumericCellValue());
                                ipc.setValorIPC(new BigDecimal(cell.getNumericCellValue()));
                                break;
                            case ExcelConstant.COLUM_2:
                                logger.debug(cell.getNumericCellValue());
                                ipc.setPorcentajeCotizacion(new BigDecimal(cell.getNumericCellValue()));
                                break;
                            default:
                                break;
                        }

                    }
                }

            }
            if(ipc.getValorIPC()!=null){
                lstIpc.add(ipc);
            }
            
        }

        return lstIpc;
    }

}
