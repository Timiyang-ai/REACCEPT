public static String readContentAsUtf8(HttpServletRequest request,
      boolean checkHeaders) throws IOException, ServletException {
    if (checkHeaders) {
      checkContentType(request);
      checkCharacterEncoding(request);
    }

    /*
     * Need to support 'Transfer-Encoding: chunked', so do not rely on
     * presence of a 'Content-Length' request header.
     */
    InputStream in = request.getInputStream();
    byte[] buffer = new byte[BUFFER_SIZE];
    ByteArrayOutputStream out = new  ByteArrayOutputStream(BUFFER_SIZE);
    try {
      while (true) {
        int byteCount = in.read(buffer);
        if (byteCount == -1) {
          break;
        }
        out.write(buffer, 0, byteCount);
      }
      return out.toString(CHARSET_UTF8);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }