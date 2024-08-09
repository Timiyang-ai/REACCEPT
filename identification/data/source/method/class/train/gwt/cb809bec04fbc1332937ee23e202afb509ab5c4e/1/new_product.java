public static String readContentAsGwtRpc(HttpServletRequest request)
      throws IOException, ServletException {
      return readContent(request, GWT_RPC_CONTENT_TYPE, CHARSET_UTF8);
  }