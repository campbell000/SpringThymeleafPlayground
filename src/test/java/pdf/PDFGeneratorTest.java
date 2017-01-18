package pdf;

import com.d3s.services.pdf.BasicHtmlPdfGenerator;
import com.d3s.services.pdf.HtmlPdfGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;

/**
 * Created by Alex_Lappy_486 on 1/16/17.
 *
 * This test suite ensures that the basic page generation works
 */
public class PDFGeneratorTest
{
    private static final String BASIC_TEST_FILE_LOC = "resources/simple.html";
    private static final String BASIC_TEST_FILE_OUTPUT = "test.pdf";
    private static final String BASIC_TEST_FILE_OUTPUT_TWO = "test2.pdf";

    @BeforeClass
    public static  void initTestSuite()
    {
        File file = new File(BASIC_TEST_FILE_OUTPUT);
        if (file.exists())
            file.delete();
    }

    public void testBasicPDFGeneration() throws Exception
    {
        HtmlPdfGenerator pdfGenerator = new BasicHtmlPdfGenerator();
        File file = new File(BASIC_TEST_FILE_LOC);
        pdfGenerator.generatePDFFromFile(file.getAbsolutePath(), new File(BASIC_TEST_FILE_OUTPUT));

        Assert.assertTrue(file.exists() && file.length() > 0);
    }
}
