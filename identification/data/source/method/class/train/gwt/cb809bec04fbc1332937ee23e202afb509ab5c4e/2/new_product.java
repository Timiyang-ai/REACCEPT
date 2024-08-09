@Deprecated
  public static String readContentAsUtf8(HttpServletRequest request,
      boolean checkHeaders) throws IOException, ServletException {
    return readContent(request, GWT_RPC_CONTENT_TYPE, CHARSET_UTF8_NAME);
  }