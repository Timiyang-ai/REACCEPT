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
      // The type must be plain text.
      if (contentType.startsWith("text/plain")) {
        // And it must be UTF-8 encoded (or unspecified, in which case we assume
        // that it's either UTF-8 or ASCII).
        if (contentType.indexOf("charset=") == -1) {
          contentTypeIsOkay = true;
        } else if (contentType.indexOf("charset=utf-8") != -1) {
          contentTypeIsOkay = true;
        }
      }
    }

    if (!contentTypeIsOkay) {
      throw new ServletException(
          "Content-Type must be 'text/plain' with 'charset=utf-8' (or unspecified charset)");
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