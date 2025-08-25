package com.ohgiraffers.exception;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/showErrorPage")
public class ExceptionHHandlerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Enumeration<String> attrName = request.getAttributeNames();
    while (attrName.hasMoreElements()) {
        System.out.println(attrName.nextElement());
    }

    Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
    String message = (String) request.getAttribute("jakarta.servlet.error.status_code");

    StringBuilder errorPage = new StringBuilder();
    errorPage.append("<!doctype html>\n")
            .append("<html>\n")
            .append("<head>\n")
            .append("</head>\n")
            .append("<body>\n")
            .append(statusCode)
            .append(" - ")
            .append(message)
            .append("</h1>")
            .append("</body>\n")
            .append("<html>\n")
            .append("<html>\n")
            .append("<html>\n")
            .append("</html>");

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.print(errorPage);

    out.close();
    }
}
