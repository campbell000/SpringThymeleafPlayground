package com.d3s.services.pdf;

import com.d3s.services.pdf.template.WebTemplate;

import java.io.File;
import java.io.OutputStream;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 */
public interface ThymeleafTemplatePDFGenerator extends HtmlPdfGenerator
{
    public File generatePDFFromTemplate(WebTemplate template, File outputFile) throws Exception;

    public void generatePDFFromTemplate(WebTemplate template, OutputStream outputStream) throws Exception;
}
