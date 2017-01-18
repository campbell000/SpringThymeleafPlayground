package com.d3s.services.pdf;

import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.OutputStream;
import java.util.Map;

/**
 * Created by Alex_Lappy_486 on 1/15/17.
 */
public interface HtmlPdfGenerator
{
    public File generatePDFFromFile(String fileName, File outputFile) throws Exception;

    public void generatePDFFromFile(String fileName, OutputStream outputStream) throws Exception;

    public File generatePDF(String html, File outputFile) throws Exception;

    public void generatePDF(String html, OutputStream outputStream) throws Exception;

    public void adjustITextRenderer(ITextRenderer renderer);
}
