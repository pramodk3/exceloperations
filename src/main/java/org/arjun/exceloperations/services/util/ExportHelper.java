package org.arjun.exceloperations.services.util;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.arjun.exceloperations.constants.ExcelOperationsConstants.*;

public class ExportHelper {
    private static final int PIXELS_PER_CHARACTER = 256;
    private static final int WIDTH_FOR_FILTER = 5000;
    private static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
    private static final short EXCEL_ROW_HEIGHT_FACTOR = 20;
    private static final int UNIT_OFFSET_LENGTH = 7;
    private static final int[] UNIT_OFFSET_MAP = new int[]{0, 36, 73, 109, 146, 182, 219};
    private static final int COLUMN0 = 0;
    private static final int COLUMN1 = 1;
    private static final int COLUMN2 = 2;
    private static final int COLUMN3 = 3;
    private static final int COLUMN4 = 4;
    private static final int COLUMN5 = 5;
    private static final int COLUMN6 = 6;
    private static final int COLUMN7 = 7;
    private static final int COLUMN8 = 8;
    private static final int COLUMN9 = 9;
    private static final int COLUMN10 = 10;
    private static final int COLUMN31 = 31;
    private static final int WIDTH150 = 150;
    private static final int WIDTH220 = 220;
    private static final int WIDTH450 = 450;

    public static List<String> getColumnHeaders() {
        return Arrays.asList(PERMIT_NUMBER, PERMIT_TYPE,
                PERMIT_TYPE_DEFINITION, PERMIT_CREATION_DATE, BLOCK, LOT,
                STREET_NUMBER, STREET_NAME, STREET_SUFFIX, DESCRIPTION, STATUS,
                STATUS_DATE, FILED_DATE, ISSUED_DATE, NUMBER_OF_EXISTING_STORIES,
                NUMBER_OF_PROPOSED_STORIES, PERMIT_EXPIRATION_DATE, ESTIMATED_COST,
                REVISED_COST, EXISTING_USE, EXISTING_UNITS, PROPOSED_USE, PROPOSED_UNITS,
                PLANSETS, EXISTING_CONSTRUCTION_TYPE, EXISTING_CONSTRUCTION_TYPE_DESCRIPTION,
                PROPOSED_CONSTRUCTION_TYPE, PROPOSED_CONSTRUCTION_TYPE_DESCRIPTION,
                SUPERVISOR_DISTRICT, NEIGHBORHOODS_ANALYSIS_BOUNDARIES, ZIPCODE,
                LOCATION, RECORD_ID);
    }

    public static void populateHeaders(SXSSFWorkbook workbook,
                                       SXSSFSheet sheet,
                                       int rowNum,
                                       int colNum,
                                       List<String> columnHeaders) {
        CellStyle headerStyle = createHeaderStyle(workbook);
        Row row = sheet.createRow(rowNum);

        for (String columnHeader : columnHeaders) {
            Cell cell = row.createCell(colNum);
            cell.setCellValue(columnHeader);
            cell.setCellStyle(headerStyle);
            int width = columnHeader.length() * PIXELS_PER_CHARACTER;
            sheet.setColumnWidth(colNum, width + WIDTH_FOR_FILTER);
            colNum++;
        }
    }

    public static CellStyle createHeaderStyle(SXSSFWorkbook wb) {
        CellStyle style = wb.createCellStyle();
        style.setWrapText(false);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style = setBorderStyle(style);

        Font hFont = wb.createFont();
        hFont.setColor(IndexedColors.WHITE.getIndex());
        hFont.setBold(true);
        style.setFont(hFont);
        return style;
    }

    public static CellStyle createCellStyle(SXSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setWrapText(false);
        BorderStyle borderStyle = BorderStyle.THIN;
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderTop(borderStyle);

        return style;
    }

    private static CellStyle setBorderStyle(CellStyle style) {
        BorderStyle borderStyle = BorderStyle.THIN;
        style.setBorderRight(borderStyle);
        style.setBorderBottom(borderStyle);
        style.setBorderLeft(borderStyle);
        style.setBorderTop(borderStyle);
        return style;
    }

    public static void setCookieListExportColumnsWidth(Workbook workbook) {
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN1, pixel2WidthUnits(WIDTH150));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN4, pixel2WidthUnits(WIDTH150));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN5, pixel2WidthUnits(WIDTH150));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN6, pixel2WidthUnits(WIDTH150));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN8, pixel2WidthUnits(WIDTH150));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN9, pixel2WidthUnits(WIDTH450));
        workbook.getSheet(OVERVIEW_PAGE).setColumnWidth(COLUMN31, pixel2WidthUnits(WIDTH450));
    }

    public static File writeDataToFile(Workbook workbook, String path) throws IOException {
        File file = new File(path);

        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);

        outputStream.close();

        return file;
    }

    public static short pixel2WidthUnits(int pxs) {
        short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR * (pxs / UNIT_OFFSET_LENGTH));
        widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
        return widthUnits;
    }

}
