
package business;

import java.text.NumberFormat;

/**
 *
 * @author josepharcelo
 */
public class Purchase
{
    private String purchdt, purchtype, transcd, transdesc;
    private double amt;
    private NumberFormat curr = NumberFormat.getCurrencyInstance();
    
    public Purchase(){
        this.purchdt= "";
        this.purchtype = "";
        this.transcd = "";
        this.transdesc = "";
        this.amt = 0;
    }

    public Purchase(String purchdt, String purchtype, String transcd, String transdesc, double amt)
    {
        this.purchdt = purchdt;
        this.purchtype = purchtype;
        this.transcd = transcd;
        this.transdesc = transdesc;
        this.amt = amt;
    }

    public String getPurchdt()
    {
        return purchdt;
    }

    public void setPurchdt(String purchdt)
    {
        this.purchdt = purchdt;
    }

    public String getPurchtype()
    {
        return purchtype;
    }

    public void setPurchtype(String purchtype)
    {
        this.purchtype = purchtype;
    }

    public String getTranscd()
    {
        return transcd;
    }

    public void setTranscd(String transcd)
    {
        this.transcd = transcd;
    }

    public String getTransdesc()
    {
        return transdesc;
    }

    public void setTransdesc(String transdesc)
    {
        this.transdesc = transdesc;
    }

    public String getAmt()
    {
        return curr.format(this.amt);
    }

    public void setAmt(double amt)
    {
        this.amt = amt;
    }
    
}
