
package business;

import java.text.NumberFormat;

/**
 *
 * @author josepharcelo
 */
public class Purchase
{
    private String purchaseDate, purchaseType, transactionCode, transactionDescription;
    private double amount;
    private NumberFormat currency = NumberFormat.getCurrencyInstance();
    
    public Purchase(){
        purchaseDate= "";
        purchaseType = "";
        transactionCode = "";
        transactionDescription = "";
        amount = 0;
    }

    public Purchase(String purchaseDate, String purchaseType, String transactionCode, 
            String transactionDescription, double amount)
    {
        this.purchaseDate = purchaseDate;
        this.purchaseType = purchaseType;
        this.transactionCode = transactionCode;
        this.transactionDescription = transactionDescription;
        this.amount = amount;
    }

    public String getPurchaseDate()
    {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate)
    {
        this.purchaseDate = purchaseDate;
    }

    public String getPurchaseType()
    {
        return purchaseType;
    }

    public void setPurchaseType(String purchaseType)
    {
        this.purchaseType = purchaseType;
    }

    public String getTransactionCode()
    {
        return transactionCode;
    }

    public void setTransactionCode(String transactionCode)
    {
        this.transactionCode = transactionCode;
    }

    public String getTransactionDescription()
    {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription)
    {
        this.transactionDescription = transactionDescription;
    }

    public String getAmount()
    {
        return currency.format(this.amount);
    }

    public void setAmount(double amount)
    {
        this.amount = amount;
    }
    
}
