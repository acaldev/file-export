package com.export.export.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.export.export.exception.ReportException;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ExportService {

    public ExportService() {
    }

    public byte[] getReportPdf(final JasperPrint jasperPrint) throws ReportException {
        try {
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (JRException e) {
            throw new ReportException(e);
        }
    }

    public byte[] getReportXlsx(final JasperPrint jasperPrint) throws ReportException {
        final JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        final byte[] rawBytes;

        try(final ByteArrayOutputStream xlsReport = new ByteArrayOutputStream()){
            xlsxExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            xlsxExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));
            xlsxExporter.exportReport();

            rawBytes = xlsReport.toByteArray();
        } catch (JRException | IOException e) {
            throw new ReportException(e);
        }

        return rawBytes;
    }
}