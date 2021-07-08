package org.arjun.exceloperations.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.arjun.exceloperations.dto.PermitDetailsDto;
import org.arjun.exceloperations.services.FileBuilder;
import org.arjun.exceloperations.services.util.DataUtil;
import org.arjun.exceloperations.services.util.ExportHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.arjun.exceloperations.constants.ExcelOperationsConstants.*;

@Service
@Slf4j
public class FileBuilderImpl implements FileBuilder {

    @Override
    public String createExcelFile(String resourceUrl) {
        String uri = resourceUrl;
        if (uri != null && !uri.isEmpty()) {
            uri = "https://data.sfgov.org/resource/p4e4-a5a7.json";
        }
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);
        return getData(result);
    }

    private String getData(String jsonData) {
        try (SXSSFWorkbook workbook = new SXSSFWorkbook()) {
            final PermitDetailsDto[] permitListDto = DataUtil.readValue(jsonData,
                    PermitDetailsDto[].class);
            SXSSFSheet permitOverviewSheet = workbook.createSheet(OVERVIEW_PAGE);
            final List<String> columnHeaders = ExportHelper.getColumnHeaders();
            int rowNum = 0;
            int colNum = 0;
            ExportHelper.populateHeaders(workbook, permitOverviewSheet, rowNum, colNum, columnHeaders);
            rowNum++;

            final CellStyle wrapStyle = ExportHelper.createCellStyle(workbook);

            for (PermitDetailsDto permit : permitListDto) {
                Row row;
                row = permitOverviewSheet.createRow(rowNum);
                populateRowForPermit(row, permit, columnHeaders, wrapStyle);
                rowNum++;
            }
            ExportHelper.setCookieListExportColumnsWidth(workbook);
            String path = "C:\\Users\\DELL\\workspace\\exceloperations\\Excel\\Permit.xlsx";
            final File file = ExportHelper.writeDataToFile(workbook, path);
            if(file.exists()) {
                return "File Created successfully at path : " + file.getAbsolutePath() + "";
            }
        } catch (IOException ex) {
            log.error("Error occured while trying to write data to excel : {} ", ex.getMessage());
        }
        return "Failed to create excel file";
    }

    private void populateRowForPermit(final Row row,
                                      final PermitDetailsDto permit,
                                      final List<String> columnHeaders,
                                      final CellStyle wrapStyle) {
        int colNum = 0;
        if (null != permit) {
            for (String columnHeader : columnHeaders) {
                Cell cell = row.createCell(colNum);
                colNum++;
                String cellValue = StringUtils.EMPTY;
                Double numericCellValue = 0.0;
                boolean isNumeric = false;
                switch (columnHeader) {
                    case PERMIT_NUMBER:
                        cellValue = permit.getPermitNumber();
                        break;
                    case PERMIT_TYPE:
                        numericCellValue = permit.getPermitType() != null
                                ? permit.getPermitType().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case PERMIT_TYPE_DEFINITION:
                        cellValue = permit.getPermitTypeDefinition();
                        break;
                    case PERMIT_CREATION_DATE:
                        cellValue = permit.getPermitCreationDate() != null ?
                                permit.getPermitCreationDate().toString() : StringUtils.EMPTY;
                        break;
                    case BLOCK:
                        cellValue = permit.getBlock();
                        break;
                    case LOT:
                        cellValue = permit.getLot();
                        break;
                    case STREET_NUMBER:
                        numericCellValue = permit.getStreetNumber() != null
                                ? permit.getStreetNumber().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case STREET_NAME:
                        cellValue = permit.getStreetName();
                        break;
                    case STREET_SUFFIX:
                        cellValue = permit.getStreetSuffix();
                        break;
                    case DESCRIPTION:
                        cellValue = permit.getDescription();
                        break;
                    case STATUS:
                        cellValue = permit.getStatus();
                        break;
                    case STATUS_DATE:
                        cellValue = permit.getStatusDate() != null
                                ? permit.getStatusDate().toString() : StringUtils.EMPTY;
                        break;
                    case FILED_DATE:
                        cellValue = permit.getFiledDate() != null
                                ? permit.getFiledDate().toString() : StringUtils.EMPTY;
                        break;
                    case ISSUED_DATE:
                        cellValue = permit.getIssuedDate() != null
                                ? permit.getIssuedDate().toString() : StringUtils.EMPTY;
                        break;
                    case NUMBER_OF_EXISTING_STORIES:
                        numericCellValue = permit.getNumberOfExistingStories() != null ?
                                permit.getNumberOfExistingStories().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case NUMBER_OF_PROPOSED_STORIES:
                        numericCellValue = permit.getNumberOfProposedStories() != null
                                ? permit.getNumberOfProposedStories().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case PERMIT_EXPIRATION_DATE:
                        cellValue = permit.getPermitExpirationDate() != null
                                ? permit.getPermitExpirationDate().toString() : StringUtils.EMPTY;
                        break;
                    case ESTIMATED_COST:
                        numericCellValue = permit.getEstimatedCost() != null
                                ? permit.getEstimatedCost() : 0;
                        isNumeric = true;
                        break;
                    case REVISED_COST:
                        numericCellValue = permit.getRevisedCost() != null
                                ? permit.getRevisedCost() : 0;
                        isNumeric = true;
                        break;
                    case EXISTING_USE:
                        cellValue = permit.getExistingUse();
                        break;
                    case EXISTING_UNITS:
                        numericCellValue = permit.getExistingUnits() != null
                                ? permit.getExistingUnits().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case PROPOSED_USE:
                        cellValue = permit.getProposedUse();
                        break;
                    case PROPOSED_UNITS:
                        numericCellValue = permit.getProposedUnits() != null
                                ? permit.getProposedUnits().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case PLANSETS:
                        numericCellValue = permit.getPlansets() != null
                                ? permit.getPlansets().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case EXISTING_CONSTRUCTION_TYPE:
                        numericCellValue = permit.getExistingConstructionType() != null
                                ? permit.getExistingConstructionType().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case EXISTING_CONSTRUCTION_TYPE_DESCRIPTION:
                        cellValue = permit.getExistingConstructionTypeDescription();
                        break;
                    case PROPOSED_CONSTRUCTION_TYPE:
                        numericCellValue = permit.getProposedConstructionType() != null
                                ? permit.getProposedConstructionType().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case PROPOSED_CONSTRUCTION_TYPE_DESCRIPTION:
                        cellValue = permit.getProposedConstructionTypeDescription();
                        break;
                    case SUPERVISOR_DISTRICT:
                        numericCellValue = permit.getSupervisorDistrict() != null
                                ? permit.getSupervisorDistrict().doubleValue() : 0;
                        isNumeric = true;
                        break;
                    case NEIGHBORHOODS_ANALYSIS_BOUNDARIES:
                        cellValue = permit.getNeighborhoodsAnalysisBoundaries();
                        break;
                    case ZIPCODE:
                        cellValue = permit.getZipcode();
                        break;
                    case LOCATION:
                        cellValue = (permit.getLocation() != null) ? permit.getLocation().toString()
                                : StringUtils.EMPTY;
                        break;
                    case RECORD_ID:
                        cellValue = permit.getRecordId();
                        break;
                    default:
                        break;
                }
                if (isNumeric) {
                    cell.setCellValue(numericCellValue);
                } else {
                    cell.setCellValue(cellValue != null ? cellValue : StringUtils.EMPTY);
                }
                cell.setCellStyle(wrapStyle);
            }
        }
    }

}
