package Project_Classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.util.Scanner;

public class Isbank extends ReadingPDF
{
    private String tax;
    private String IBAN;
    private final int lineofDate = 7;
    private final int rowofDate = 1;
    public Isbank(String tax , String IBAN)
    {
        super(tax , IBAN , 14 , 12);
    }
    public String getDate(String filePath)
    {
        try {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readDate = stripper.getText(pdDocument);
            Scanner input = new Scanner(readDate);
            for (int i = 1; i < lineofDate; i++) {
                input.nextLine();
            }
            for (int i = 1; i < rowofDate; i++) {
                input.next();
            }

            return input.next().replace("", "");
        }catch(Exception ex){}
        return "";

    }
}
