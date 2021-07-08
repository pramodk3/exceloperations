package org.arjun.exceloperations.controllers;

import org.arjun.exceloperations.services.FileBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/exceloperations/v1/")
public class ExcelWriterController {

    private FileBuilder fileBuilder;

    @Autowired
    public ExcelWriterController(FileBuilder fileBuilder) {
        this.fileBuilder = fileBuilder;
    }

    @GetMapping("createexcel")
    public String readJsonData(@RequestBody @Validated String resourceUrl) {
        return fileBuilder.createExcelFile(resourceUrl);
    }
}
