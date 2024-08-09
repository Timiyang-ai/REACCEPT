  private String readContentAsUtf8(String content) throws IOException, ServletException {
    HttpServletRequest m = new MockReqContentType(null, content);
    // ignore Content-Type, read as UTF-8
    return RPCServletUtils.readContent(m, null, null);
  }