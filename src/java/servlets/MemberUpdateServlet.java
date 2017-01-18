
package servlets;

import business.Member;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
@WebServlet(name = "MemberUpdateServlet", urlPatterns = {"/MemberUpdate"})
public class MemberUpdateServlet extends HttpServlet
{
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String query, msg = "", URL = "/MemberScreen.jsp";
        String lastName = "", firstName = "", middleName = "", status ="", membershipDate="";
        long newPassword = 0;
        
        String dbURL = "jdbc:mysql://localhost:3306/club";
        String dbUser = "root";
        String dbPwd = "uftbutefade1";
        
        String validateName = "^\\p{L}+[\\p{L}\\p{Z}\\p{P}]{0,}";
        String validateDateFormat = "([0-9]{4})-([0-9]{2})-([0-9]{2})";

        try {
            Member m = (Member) request.getSession().getAttribute("m");
            Member n = m;
            try {
                lastName = request.getParameter("lastName");
                if (!lastName.isEmpty() && lastName.matches(validateName)) {
                    n.setLastName(lastName);
                }
                else{
                    msg += "Input error. Lastname is empty or has invalid characters.<br>";
                }
            } catch (Exception e) {
                msg += "Lastname error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                firstName = request.getParameter("firstName");
                if (!firstName.isEmpty() && firstName.matches(validateName)) {
                    n.setFirstName(firstName);
                }
                else{
                    msg += "Input error. Firstname is empty or has invalid characters.<br>";
                }
            } catch (Exception e) {
                msg += "Firstname error:" + e.getMessage() + "<br>"; 
            }
             
            try {
                middleName = request.getParameter("middleName");
                if (!middleName.isEmpty() && middleName.matches(validateName)) {
                    n.setMiddleName(middleName);
                }
                else{
                    msg += "Input error. Middlename is empty or has invalid characters.<br>";
                }
            } catch (Exception e) {
                msg += "Middlename error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                status = request.getParameter("status");
                if (!status.isEmpty() && status.matches(validateName)) {
                    n.setStatus(status);
                }
                else{
                    msg += "Input error. Status is empty or has invalid characters.<br>";
                }
            } catch (Exception e) {
                msg += "Lastname error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                membershipDate = request.getParameter("membershipDate");
                if (!membershipDate.isEmpty() && validateDate(membershipDate)) {
                    n.setMembershipDate(membershipDate);
                }
                else{
                    msg += "Input error. Date is empty or does not match YYYY-MM-DD pattern.<br>";
                }
            } catch(ParseException e) {
                msg += "Input error. Date is empty or does not match YYYY-MM-DD pattern.<br>";
            } catch (Exception e) {
                msg += "Membership Date error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                newPassword = 
                        Long.parseLong(request.getParameter("password"));
                if (newPassword > 0) {
                    n.setPassword(newPassword);
                } else {
                    msg += "Password field illegal<br>";
                }
            } catch (Exception e) {
                msg += "Missing/bad password<br>";
            }
            
            if (msg.isEmpty()) {
                Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPwd);
                // Prepared statement to prevent sql injection attacks
                query = "UPDATE tblmembers SET " +
                        " LastName = ?, " +
                        " FirstName = ?," +
                        " MiddleName = ?, " +
                        " Status = ?, " +
                        " MemDt = ?, " + 
                        " Password = ? " +
                        " WHERE MemID = ? ";
                
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, n.getLastName());
                ps.setString(2, n.getFirstName());
                ps.setString(3, n.getMiddleName());
                ps.setString(4, n.getStatus());
                ps.setString(5, n.getMembershipDate());
                ps.setLong(6, n.getPassword());
                ps.setString(7, n.getMemberId());
                
                int rc = ps.executeUpdate();
                if (rc == 0) {
                    msg += "Update failed: no changes <br>.";
                } else if (rc == 1) {
                    msg += "Member profile successfully updated!<br>";
                    m = n;
                } else {
                    msg += "Warning: " + rc + " records updated.<br>";
                }
            }
        } catch (SQLException e) {
            msg += "SQL Error: " + e.getMessage() + "<br>";
        } catch (Exception e) {
            msg += "General exception: " + e.getMessage() + "<br>";
        }
        
        request.setAttribute("msg", msg);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(URL);
        dispatcher.forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

    private boolean validateDate(String memdt) throws ParseException
    {
        boolean valid = false;
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd");
        dateFormatter.setLenient(false);
        if (!memdt.isEmpty()) {
            Date date = dateFormatter.parse(memdt);
            valid = true;
        }
        return valid;
    }

}
