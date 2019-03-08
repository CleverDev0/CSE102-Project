package Project_Classes;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

public class ReadingPDF
{
    private int fee; // Aidat tutarı / amount of fee
    private String IBAN; // IBAN OF RECEIVER.
    private int lineofIBAN; // hangi satırda IBAN bulunduğunu saklar.
    private int lineofAmount; // hangi satırda ödeme miktarı bulunduğunu saklar.

    public ReadingPDF (int fee , String IBAN , int lineofAmount , int lineofIBAN)
    {
        this.fee=fee;
        this.IBAN=IBAN;
        this.lineofAmount = lineofAmount;
        this.lineofIBAN = lineofIBAN;
    }
    //TODO public int getDate(){} // Son ödeme tarihi eklenecek.

    public boolean isIBANvalid(String filePath , String IBANofManager)
    {
        try
        {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readIBAN = stripper.getText(pdDocument);
            Pattern pattern = Pattern.compile(".*" + IBANofManager + ".*");
            Matcher matcher = pattern.matcher(readIBAN);

            return matcher.find();
        }
        catch(Exception ex)
        {

        }
        return false;
    }




}
