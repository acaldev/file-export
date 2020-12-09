package com.export.export.controller;

import com.export.export.entities.mariadb.Employee;
import com.export.export.service.EmployeeService;
import com.export.export.service.ExportService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class BaseController {

    @Autowired
    EmployeeService service;

    @Autowired
    ExportService exportService;

    @CrossOrigin(origins = "http://app.test")
    @GetMapping(value = "/", produces= "application/json", params = { "page", "size" })
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> list(@RequestParam("page") int page, @RequestParam("size") int size) {
        return service.page(page, size);
    }

    public JasperReport generateReportTemplate() throws JRException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/reports/employee_list.jrxml");
        JasperReport report = JasperCompileManager.compileReport(jasperStream);
        return report;
    }

    @GetMapping(path = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    @ResponseBody
    public HttpEntity<byte[]> getPdf(@RequestParam("page") int page, @RequestParam("size") int size) throws Exception {
        //Get JRXML template from resources folder
        JasperReport report = this.generateReportTemplate();
        //Parameters Set
        Map<String, Object> params = new HashMap<>();
        List<Employee> employeeList = (List<Employee>) service.page(page, size).getContent();
        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(employeeList);
        params.put("datasource", dataSource);
        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        // Export PDF
        final byte[] data = exportService.getReportPdf(jasperPrint);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_PDF);
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.pdf");
        header.setContentLength(data.length);
        return new HttpEntity<byte[]>(data, header);
    }

    @GetMapping(path = "/xlsx")
    @ResponseBody
    public HttpEntity<byte[]> getXlsx(@RequestParam("page") int page, @RequestParam("size") int size, HttpServletResponse response) throws Exception {
        final byte[] rawBytes;
        //Get JRXML template from resources folder
        JasperReport report = this.generateReportTemplate();
        //Parameters Set
        Map<String, Object> params = new HashMap<>();
        List<Employee> employeeList = (List<Employee>) service.page(page, size).getContent();
        //Data source Set
        JRDataSource dataSource = new JRBeanCollectionDataSource(employeeList);
        params.put("datasource", dataSource);
        //Make jasperPrint
        JasperPrint jasperPrint = JasperFillManager.fillReport(report, params, dataSource);
        // Export XLSX
        final byte[] data = exportService.getReportXlsx(jasperPrint);
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        header.set(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=employeeReport.xlsx");
        header.setContentLength(data.length);
        return new HttpEntity<byte[]>(data, header);
    }

}
