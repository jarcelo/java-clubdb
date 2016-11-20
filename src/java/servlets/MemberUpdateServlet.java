
package servlets;

import business.Member;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        String sql, msg = "", URL = "/MemberScreen.jsp";
        String lname = "", fname = "", mname = "", status ="", memdt="";
        long newPassword = 0;
        
        String dbURL = "jdbc:mysql://localhost:3306/club";
        String dbUser = "root";
        String dbPwd = "uftbutefade1";
        
        try {
            Member m = (Member) request.getSession().getAttribute("m");
            Member n = m;
            try {
                lname = request.getParameter("lastname");
                if (!lname.isEmpty()) {
                    n.setLastnm(lname);
                }
                else{
                    msg += "Last name empty.<br>";
                }
            } catch (Exception e) {
                msg += "Lastname error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                fname = request.getParameter("firstname");
                if (!fname.isEmpty()) {
                    n.setFirstnm(fname);
                }
                else{
                    msg += "First name empty.<br>";
                }
            } catch (Exception e) {
                msg += "Firstname error:" + e.getMessage() + "<br>"; 
            }
             
            try {
                mname = request.getParameter("middlename");
                if (!mname.isEmpty()) {
                    n.setMiddlenm(mname);
                }
                else{
                    msg += "Middle name empty.<br>";
                }
            } catch (Exception e) {
                msg += "Middle name error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                status = request.getParameter("status");
                if (!status.isEmpty()) {
                    n.setStatus(status);
                }
                else{
                    msg += "Last name empty.<br>";
                }
            } catch (Exception e) {
                msg += "Lastname error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                memdt = request.getParameter("memdt");
                if (!memdt.isEmpty()) {
                    n.setMemdt(memdt);
                }
                else{
                    msg += "Member date empty.<br>";
                }
            } catch (Exception e) {
                msg += "Memberdate error:" + e.getMessage() + "<br>"; 
            }
            
            try {
                newPassword = 
                        Long.parseLong(request.getParameter("psswd"));
                if (newPassword > 0) {
                    n.setPassword(newPassword);
                } else {
                    msg += "Password field illegal<br>";
                }
            } catch (Exception e) {
                msg += "Missing/bad password<br>";
            }
            
            if (msg.isEmpty()) {
                //update database
                Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPwd);
                //prepared statement to prevent sql injection attacks
                sql = "UPDATE tblmembers SET " +
                        " LastName = ?, " +
                        " FirstName = ?," +
                        " MiddleName = ?, " +
                        " Status = ?, " +
                        " MemDt = ?, " + 
                        " Password = ? " +
                        " WHERE MemID = ? ";
                
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, n.getLastnm());
                ps.setString(2, n.getFirstnm());
                ps.setString(3, n.getMiddlenm());
                ps.setString(4, n.getStatus());
                ps.setString(5, n.getMemdt());
                ps.setLong(6, n.getPassword());
                ps.setString(7, n.getMemid());
                
                int rc = ps.executeUpdate();
                if (rc == 0) {
                    msg += "Update failed: no changes <br>.";
                } else if (rc == 1) {
                    msg += "Member Updated!<br>";
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

}
