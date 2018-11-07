/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Scanner;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Data;

/**
 *
 * @author khuyenn
 */
public class Index extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String getParam(String s, HttpServletRequest request) {
        try {
            String res = request.getParameter(s);
            if (res == null) {
                return "";
            } else if ("".equals(res.trim())) {
                return "";
            }
            return res.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public synchronized void appendStrToFile(String str) {
        try {
            FileWriter fw = new FileWriter("/home/web2/web2.log", true);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(str + "\n");
            out.close();
        } catch (IOException e) {
            System.out.println("exception occoured" + e);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ScriptException {
        int max = 10, start = 0;
        String page = request.getParameter("page");
        String crime = getParam("crime", request);
        String number = getParam("number", request);
        String date = getParam("date", request);
        String location = getParam("location", request);
        String neighborhood = getParam("neighborhood", request);
        String ltd = getParam("ltd", request);
        if (page != null) {
            try {
                start = Integer.parseInt(page) * 10;
                if (start >= 100) {
                    start = 90;
                    page = "9";
                }
                if (start < 0) {
                    start = 0;
                    page = "0";
                }
            } catch (Exception e) {
                start = 0;
                page = "0";
            }
        } else {
            page = "0";
        }
        if (Shared.l.size() == 0) {
            File f = new File("/opt/data.csv");
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line[] = sc.nextLine().split(",");
                Data d = new Data();
                d.setId(line[0]);
                d.setCrime(line[1]);
                d.setNumber(line[2]);
                d.setDate(line[3]);
                d.setLocation(line[4]);
                d.setBeat(line[5]);
                d.setNeighborhood(line[6]);
                d.setNpu(line[7]);
                d.setLat(line[8]);
                d.setLongtitude(line[9]);
                Shared.l.add(d);
            }
        }
        if (date.length() > 0) {
            String ip = request.getRemoteAddr();
            String dd = date;
            if (date.length() > 80) {
                date = date.substring(0, 80);
            }
            try {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
                ScriptContext context = engine.getContext();
                String js = String.format("var d = Date.parse(\"%s\"); d = new Date(d); print((\"0\" + (d.getMonth()+1)).slice(-2) + \"/\" + (\"0\" + d.getDate()).slice(-2) + \"/\" + d.getFullYear());", date);
                StringWriter writer = new StringWriter();
                context.setWriter(writer);
                engine.eval(js);
                String output = writer.toString();
                date = output.trim();
            } catch (Exception e) {
                date = e.toString();
            }
            appendStrToFile(ip + " " + dd + " " + date);
        }
        request.setAttribute("page", Integer.parseInt(page));
        request.setAttribute("date", date);
        request.setAttribute("crime", crime);
        request.setAttribute("neighborhood", neighborhood);
        request.setAttribute("location", location);
        request.setAttribute("ltd", ltd);
        request.setAttribute("number", number);
        ArrayList<Data> res = new ArrayList();
        int i = 0;
        for (Data d : Shared.l) {
            if (i >= start && i < start + max) {
                if (crime.length() > 0 && d.getCrime().contains(crime)) {
                    res.add(d);
                } else if (number.length() > 0 && d.getNumber().contains(number)) {
                    res.add(d);
                } else if (date.length() > 0 && d.getDate().contains(date)) {
                    res.add(d);
                } else if (neighborhood.length() > 0 && d.getNeighborhood().contains(neighborhood)) {
                    res.add(d);
                } else if (location.length() > 0 && d.getLocation().contains(location)) {
                    res.add(d);
                } else if (ltd.length() > 0 && d.getLat().contains(ltd)) {
                    res.add(d);
                } else if (ltd.length() > 0 && d.getLongtitude().contains(ltd)) {
                    res.add(d);
                } else if (crime.length() == 0 && number.length() == 0 && date.length() == 0 && neighborhood.length() == 0 && location.length() == 0 && ltd.length() == 0) {
                    res.add(d);
                }
            }
            i++;
        }
        request.setAttribute("datalst", res);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
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
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
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
        try {
            processRequest(request, response);

        } catch (ScriptException ex) {
            ex.printStackTrace();
        }
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
