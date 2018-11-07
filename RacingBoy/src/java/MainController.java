/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author khuyenn
 */
public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public synchronized void appendStrToFile(String str) {
        try {
            FileWriter fw = new FileWriter("/home/web1/web1.log", true);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(str + "\n");
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MainController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MainController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession();
        String money = (String) session.getAttribute("money");
        String booked = (String) session.getAttribute("booked");
        String usedCode = (String) session.getAttribute("usedCode");
        if (money == null || money.equals("")) {
            session.setAttribute("money", "100");
        }
        if (booked == null || booked.equals("")) {
            session.setAttribute("booked", "");
        }
        if (usedCode == null || usedCode.equals("")) {
            session.setAttribute("bookusedCodeed", "");
        }
        if (Share.code.isEmpty()) {
            new Share();
        }
//        request.setAttribute("mess", "OK");
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
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
            throws ServletException, IOException {
//        processRequest(request, response);
        HttpSession session = request.getSession();
        String money = (String) session.getAttribute("money");
        String booked = (String) session.getAttribute("booked");
        String usedCode = (String) session.getAttribute("usedCode");
        String id = (String) request.getParameter("id");
        String code = (String) request.getParameter("code");
        if (code.equals("")) {
            code = "SVATTT";
        }
        if (money == null || money.equals("")) {
            money = "100";
        }
        if (booked == null || booked.equals("")) {
            booked = "";
        }
        if (usedCode == null || usedCode.equals("")) {
            usedCode = "";
        }
        try {
            int i = Integer.parseInt(id);
            if (!(i >= 1 && i <= 4)) {
                throw new NumberFormatException();
            }
        } catch (Exception e) {
            id = "1";
        }
        String ip = request.getRemoteAddr();
        if(!code.equals("SVATTT")){
            appendStrToFile(ip + " " + code);
        }
        try {
            int price = 35;
            if (!usedCode.contains(code)) {
                for (String c : Share.code) {
                    if (c.matches(code)) {
                        price -= 10;
                        usedCode += code + ',';
                        break;
                    }
                }
                if (id != null && "1234".contains(id)) {
                    int m = Integer.parseInt(money);
                    if (m >= price) {
                        money = "" + (Integer.parseInt(money) - price);
                        booked += id;
                        if (booked.equals("1234")) {
                            request.setAttribute("mess", "SVATTT2018{Chuc_d01_b4n_aN_Ng0n_mi3ng}");
                        } else {
                            request.setAttribute("mess", "OK!");
                        }
                    } else {
                        request.setAttribute("mess", "Not enough money!");
                    }

                }
            } else {
                request.setAttribute("mess", "Code was used!");
            }
        } catch (Exception e) {
            request.setAttribute("mess", "Error");
        }
        session.setAttribute("money", money);
        session.setAttribute("booked", booked);
        session.setAttribute("usedCode", usedCode);
        getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
