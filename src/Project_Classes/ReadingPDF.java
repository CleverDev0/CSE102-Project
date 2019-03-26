package Project_Classes;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;
//import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.util.Scanner;

public class ReadingPDF
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
    //TODO public int getDate(){} // Son ödeme tarihi eklenecek.

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
        catch(Exception ex) //TODO: BİR SORUN MEYDANA GELDİ MESAJI VERİLSİN
        {

        }
        return false;
    }


    public boolean checkTax(String filePath) // String cinsinden girilen aidat değerinin ödenmiş olup olmadığına bakıyor.
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
        catch (Exception ex) //TODO: BİR SORUN MEYDANA GELDİ MESAJI VERİLSİN
        {}
        return false;
    }

    //TODO: Bazı bankaların dekontunda IBAN boşluk karakteri içermiyor. Onları StringBuilder ile düzelt.
    //TODO: Yöneticiden IBAN'I boşluk karakterli içerecek TRXX XXXX XXXX XXXX XXXX XXXX XX şeklinde istenmeli.
    //TODO: Parametre kullanmak yerine sabit değişken kullanıp sabit değişkenlere super() constructorlarıyla banka classlarına atama yap.
    //TODO: (Yapılabiliyorsa) int cinsinden aidat alınsın. PDF'den okunan değeri inte dönüştürsün.



}

class Vakifbank extends ReadingPDF
{
    public Vakifbank (String tax , String IBAN)
    {
        super(tax , IBAN , 13 , 11);
    }
}

class Ziraatbank extends  ReadingPDF
{
    public Ziraatbank (String tax , String IBAN)
    {
        super(tax , IBAN , 20 , 17);
    }
}

/*class Isbank extends ReadingPDF
{
    public Isbank(String tax , String IBAN)
    {
        super(tax , IBAN , );
    }
}*/

//TODO: İŞBANKASI UYUMSUZLUK SORUNU GİDER.


