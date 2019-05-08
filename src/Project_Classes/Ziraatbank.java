package Project_Classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

import java.io.File;
import java.util.Scanner;

public class Ziraatbank extends  ReadingPDF
{
    private String tax;
    private String IBAN;
    private final int lineofDate = 10;
    private final int rowofDate = 3;

    public Ziraatbank (String tax , String IBAN)
    {
        super(tax , IBAN.replace(" " , "") , 20 , 17);
        this.IBAN=IBAN.replace(" " , "");
        this.tax=tax;
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
