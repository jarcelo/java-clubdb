
package servlets;

import business.Member;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class ClubLogonServlet extends HttpServlet
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
        
        String URL = "/Logon.jsp";
        String msg = "", sql = "", userid = "";
        long passattempt;
        Member member;
        
        String dbURL = "jdbc:mysql://localhost:3306/club";
        
        String dbUser = "root";
        String dbPwd = "uftbutefade1";
        
        try {
            userid = request.getParameter("userid").trim();
            passattempt = Long.parseLong(request.getParameter("password").trim());
            
            sql = "SELECT * FROM tblMembers WHERE MemID = '" + userid + "'";
            Connection connection = DriverManager.getConnection(dbURL, dbUser, dbPwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                member = new Member();
                member.setMemid(userid);
                member.setPassword(resultSet.getLong("Password"));
                member.setPassattempt(passattempt);
                if (member.isAuthenticated()) {
                    member.setLastnm(resultSet.getString("LastName"));
                    member.setFirstnm(resultSet.getString("FirstName"));
                    member.setMiddlenm(resultSet.getString("MiddleName"));
                    member.setStatus(resultSet.getString("status"));
                    member.setMemdt(resultSet.getString("MemDt"));
                    URL = "/MemberScreen.jsp";
                    msg = "Member Authenticated! <br>";
                } else {
                    msg = "Member found but not authenticated. <br>";
                }
                request.getSession().setAttribute("m", member);
            } else {
                msg = "Userid not found.<br>";
            }
        } catch(NumberFormatException e) {
            msg = "Illegal password<br>";
        } catch(SQLException e) {
            msg = "SQL Exception: " + e.getMessage() + "<br>";
        }
        catch (Exception e) {
        }
        // Add cookie for userID
        Cookie uid = new Cookie("userid", userid);
        uid.setMaxAge(60*10);
        uid.setPath("/");
        response.addCookie(uid);
        
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
