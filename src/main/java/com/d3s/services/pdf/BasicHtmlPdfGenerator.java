package com.d3s.services.pdf;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 */
public class BasicHtmlPdfGenerator implements HtmlPdfGenerator
{
    ITextRenderer renderer;

    public BasicHtmlPdfGenerator()
    {
        this.renderer = new ITextRenderer();
    }

    @Override
    public File generatePDFFromFile(String fileName, File outputFile) throws Exception {
        String html = generateContentString(new File(fileName));
        return generatePDF(html, outputFile);
    }

    @Override
    public void generatePDFFromFile(String fileName, OutputStream outputStream) throws Exception {
        String html = generateContentString(new File(fileName));
        generatePDF(html, outputStream);
    }

    @Override
    public File generatePDF(String html, File outputFile) throws Exception
    {
        // Set up output stream from given file
        FileOutputStream outputStream = null;
        outputStream = new FileOutputStream(outputFile);
        generatePDF(html, outputStream);

        return outputFile;
    }

    @Override
    public void generatePDF(String html, OutputStream outputStream) throws Exception
    {
        try
        {
            adjustITextRenderer(renderer);
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
        }
        finally {
            if (outputStream != null)
                outputStream.close();
        }
    }

    @Override
    public void adjustITextRenderer(ITextRenderer renderer)
    {
        // Do nothing for the default implementation
    }

    protected String generateContentString(File file) throws IOException
    {
        return FileUtils.readFileToString(file, "UTF-8");
    }
}
