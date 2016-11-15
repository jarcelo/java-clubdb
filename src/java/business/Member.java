
package business;

/**
 *
 * @author josepharcelo
 */
public class Member
{
    private String memid, lastnm, firstnm, middlenm;
    private String status, memdt;
    private long password, passattempt;

    public Member()
    {
        memid = "";
        lastnm = "";
        firstnm = "";
        middlenm = "";
        status = "";
        memdt = "";
        password = 0;
        passattempt = -1;
    }

    public String getMemid()
    {
        return memid;
    }

    public void setMemid(String memid)
    {
        this.memid = memid;
    }

    public String getLastnm()
    {
        return lastnm;
    }

    public void setLastnm(String lastnm)
    {
        this.lastnm = lastnm;
    }

    public String getFirstnm()
    {
        return firstnm;
    }

    public void setFirstnm(String firstnm)
    {
        this.firstnm = firstnm;
    }

    public String getMiddlenm()
    {
        return middlenm;
    }

    public void setMiddlenm(String middlenm)
    {
        this.middlenm = middlenm;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMemdt()
    {
        return memdt;
    }

    public void setMemdt(String memdt)
    {
        this.memdt = memdt;
    }

    public long getPassword()
    {
        return password;
    }

    public void setPassword(long password)
    {
        this.password = password;
    }

    public long getPassattempt()
    {
        return passattempt;
    }

    public void setPassattempt(long passattempt)
    {
        this.passattempt = passattempt;
    }
    
    public boolean isAuthenticated() {
        if (password > 0) {
            if (password == this.passattempt) {
                return true;
            }
        }
        return false;
    }
}
