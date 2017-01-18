
package business;

/**
 *
 * @author josepharcelo
 */
public class Member
{
    private String memberId, lastName, firstName, middleName;
    private String status, membershipDate;
    private long password, passwordAttempt;

    public Member()
    {
        memberId = "";
        lastName = "";
        firstName = "";
        middleName = "";
        status = "";
        membershipDate = "";
        password = 0;
        passwordAttempt = -1;
    }

    public String getMemberId()
    {
        return memberId;
    }

    public void setMemberId(String memberId)
    {
        this.memberId = memberId;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getMiddleName()
    {
        return middleName;
    }

    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMembershipDate()
    {
        return membershipDate;
    }

    public void setMembershipDate(String membershipDate)
    {
        this.membershipDate = membershipDate;
    }

    public long getPassword()
    {
        return password;
    }

    public void setPassword(long password)
    {
        this.password = password;
    }

    public long getPasswordAttempt()
    {
        return passwordAttempt;
    }

    public void setPasswordAttempt(long passwordAttempt)
    {
        this.passwordAttempt = passwordAttempt;
    }
    
    public boolean isAuthenticated() {
        if (password > 0) {
            if (password == this.passwordAttempt) {
                return true;
            }
        }
        return false;
    }
}
