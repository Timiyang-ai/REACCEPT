protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet url-pattern in web.xml</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>c</h1>");
        out.println("</body>");
        out.println("</html>");
    }