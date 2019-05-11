package Project_Classes;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
//import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public abstract class ReadingPDF
{
    private String tax; // Aidat tutarı
    private String IBAN; // ALICININ (YÖNETİCİNİN) IBANI.
    private int lineofIBAN; // hangi satırda IBAN bulunduğunu saklar.
    private int lineofAmount; // hangi satırda ödeme miktarı bulunduğunu saklar.

    public ReadingPDF (String tax , String IBAN , int lineofAmount , int lineofIBAN)
    {
        this.tax=tax;
        this.IBAN=IBAN;
        this.lineofAmount = lineofAmount;
        this.lineofIBAN = lineofIBAN;
    }
    public abstract String getDate(String filePath);


    public boolean correctIBAN(String filePath)
    {
        try
        {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readIBAN = stripper.getText(pdDocument);
            Scanner input = new Scanner(readIBAN);
            for(int i = 1 ; i<lineofIBAN ; i++)
            {
                input.nextLine();
            }
                return input.nextLine().matches(".*"+IBAN+".*");


        }
        catch(Exception ex)
        {

        }
        return false;
    }


    public boolean checkTax(String filePath)
    {

        try
        {
            File uploadedFile = new File(filePath);
            PDDocument pdDocument = PDDocument.load(uploadedFile);
            PDFTextStripper stripper = new PDFTextStripper();
            String readFee = stripper.getText(pdDocument);
            Scanner input = new Scanner(readFee);
            for (int i = 1; i < lineofAmount; i++)
            {
                input.nextLine();
            }

            return input.nextLine().matches(".*" + tax + ".*");
        }
        catch (Exception ex)
        {}
        return false;
    }



}