package Project_Classes;

public class TEB extends ReadingPDF
{
    private String tax;
    private String IBAN;
    private final int lineofDate = 10;
    private final int rowofDate = 3;
    public TEB (String tax , String IBAN) { super (tax , IBAN , 9 , 7);}
}
