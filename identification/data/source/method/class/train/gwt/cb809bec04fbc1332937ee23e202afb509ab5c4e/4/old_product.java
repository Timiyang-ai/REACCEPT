public static String readContentAsUtf8(HttpServletRequest request)
      throws IOException, ServletException {
    int contentLength = request.getContentLength();
    if (contentLength == -1) {
      // Content length must be known.
      throw new ServletException("Content-Length must be specified");
    }

    String contentType = request.getContentType();
    boolean contentTypeIsOkay = false;
    // Content-Type must be specified.
    if (contentType != null) {
      contentType = contentType.toLowerCase();
      /*
       * The Content-Type must be text/x-gwt-rpc.
       * 
       * NOTE:We use startsWith because some servlet engines, i.e. Tomcat, do
       * not remove the charset component but others do.
       */
      if (contentType.startsWith(EXPECTED_CONTENT_TYPE)) {
        String characterEncoding = request.getCharacterEncoding();
        if (characterEncoding != null) {
          /*
           * TODO: It would seem that we should be able to use equalsIgnoreCase
           * here instead of indexOf. Need to be sure that servlet engines
           * return a properly parsed character encoding string if we decide to
           * make this change.
           */
          if (characterEncoding.toLowerCase().indexOf(
              CHARSET_UTF8.toLowerCase()) != -1) {
            contentTypeIsOkay = true;
          }
        }
      }
    }

    if (!contentTypeIsOkay) {
      throw new ServletException("Content-Type must be '"
          + EXPECTED_CONTENT_TYPE + "' with '" + EXPECTED_CHARSET + "'.");
    }

    InputStream in = request.getInputStream();
    try {
      byte[] payload = new byte[contentLength];
      int offset = 0;
      int len = contentLength;
      int byteCount;
      while (offset < contentLength) {
        byteCount = in.read(payload, offset, len);
        if (byteCount == -1) {
          throw new ServletException("Client did not send " + contentLength
              + " bytes as expected");
        }
        offset += byteCount;
        len -= byteCount;
      }
      return new String(payload, CHARSET_UTF8);
    } finally {
      if (in != null) {
        in.close();
      }
    }
  }