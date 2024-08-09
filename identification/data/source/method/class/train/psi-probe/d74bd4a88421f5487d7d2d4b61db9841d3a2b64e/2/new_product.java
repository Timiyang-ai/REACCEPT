@Override
  public void invoke(Request request, Response response) throws IOException, ServletException {
    getNext().invoke(request, response);

    HttpServletRequest servletRequest = request.getRequest();
    HttpSession session = servletRequest.getSession(false);
    if (session != null) {
      String ip = IpInfo.getClientAddress(servletRequest);
      session.setAttribute(ApplicationSession.LAST_ACCESSED_BY_IP, ip);
    }
  }