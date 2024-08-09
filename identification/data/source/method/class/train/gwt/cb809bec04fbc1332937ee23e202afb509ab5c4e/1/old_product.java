public static String readContentAsUtf8(HttpServletRequest request)
      throws IOException, ServletException {
    return readContentAsUtf8(request, true);
  }