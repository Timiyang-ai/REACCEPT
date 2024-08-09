public static String readContent(HttpServletRequest request,
      String expectedContentType, String expectedCharSet)
      throws IOException, ServletException {
    if (expectedContentType != null) {
      checkContentTypeIgnoreCase(request, expectedContentType);
    }
    if (expectedCharSet != null) {
      checkCharacterEncodingIgnoreCase(request, expectedCharSet);
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
      String contentCharSet = expectedCharSet != null
          ? expectedCharSet : CHARSET_UTF8;
      return out.toString(contentCharSet);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }