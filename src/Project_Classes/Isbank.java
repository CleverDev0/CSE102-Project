package Project_Classes;

public class Isbank extends ReadingPDF
{
    private String tax;
    private String IBAN;
    public Isbank(String tax , String IBAN)
    {
        super(tax , IBAN , 14 , 12);
    }
}
