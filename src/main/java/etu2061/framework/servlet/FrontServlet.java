package etu2061.framework.servlet;

import etu2061.framework.Mapping;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.*;
import java.util.HashMap;

public class FrontServlet extends HttpServlet {
    HashMap<String, Mapping> MappingUrls;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Ma Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Bienvenue sur ma Servlet!</h1>");
            out.println("</body>");
            out.println("</html>");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
