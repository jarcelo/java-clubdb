
package servlets;

import business.ConnectionPool;
import business.Member;
import business.Purchase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author josepharcelo
 */
public class ShowPurchasesServlet extends HttpServlet
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
        String URL = "/MemberScreen.jsp";
        String sql = "", msg = "", mo = "", dy = "", yr ="";
        String sqlBal = "";
        String sqlwhere = "";
        try {
            Member m = (Member) request.getSession().getAttribute("m");
            mo = request.getParameter("month");
            dy = request.getParameter("day");
            yr = request.getParameter("year");
            
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection conn = pool.getConnection();
            Statement s = conn.createStatement();
            
            if (mo.isEmpty() || dy.isEmpty() || yr.isEmpty()) {
                sqlwhere = "";
            } else {
                sqlwhere = " AND p.purchasedt >= '" + yr + "-" + mo + "-" + dy + "' ";
            }
            sql = "SELECT p.memID, p.PurchaseDt, p.TransType, " +
                    " p.TransCd, c.TransDesc, p.Amount " +
                    " FROM tblPurchases p, tblCodes c " +
                    " WHERE p.transcd = c.transcd " +
                    " AND p.memid = '" + m.getMemid() + "'" +
                    sqlwhere + 
                    " ORDER BY p.purchaseDt";
            
            ResultSet r = s.executeQuery(sql);
            ArrayList<Purchase> pur = new ArrayList<>();
            while(r.next()){
                Purchase p = new Purchase(
                        r.getString("PurchaseDt"),
                        r.getString("TransType"),
                        r.getString("TransCd"),
                        r.getString("TransDesc"),
                        r.getDouble("Amount"));
                pur.add(p);
            }
            r.last();
            msg = "Total Records = " + r.getRow();
            URL = "/Purchases.jsp";
            request.setAttribute("pur", pur);
            
            //Credit
            PreparedStatement credit = conn.prepareStatement( "SELECT SUM(p.Amount) " +
                    " FROM tblPurchases p " +
                    " WHERE p.TransType = 'C' " +
                    " AND p.memid = '" + m.getMemid() + "'" +
                    sqlwhere);
            ResultSet creditTotal = credit.executeQuery();
            creditTotal.next();
            
            // Debit 
            PreparedStatement debit = conn.prepareStatement ( "SELECT SUM(p.Amount) " +
                    " FROM tblPurchases p " +
                    " WHERE p.TransType = 'D' " +
                    " AND p.memid = '" + m.getMemid() + "'" +
                    sqlwhere);
            ResultSet debitTotal = debit.executeQuery();
            debitTotal.next();
            
            double balance = Double.parseDouble(debitTotal.getString(1)) - Double.parseDouble(creditTotal.getString(1));
            request.setAttribute("bal", balance);
            
            //URL = "/Purchases.jsp";
            
        } catch (SQLException e) {
            msg = "SQL Exception: " + e.getMessage();
        } catch (Exception e) {
        }
        request.setAttribute("msg", msg);
        RequestDispatcher disp = getServletContext().getRequestDispatcher(URL);
        disp.forward(request, response);
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
