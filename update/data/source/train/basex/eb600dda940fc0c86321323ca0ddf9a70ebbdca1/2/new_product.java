protected static void put(final String u, final InputStream is, final MediaType type)
      throws IOException {

    final IOUrl url = new IOUrl(root + u);
    final HttpURLConnection conn = (HttpURLConnection) url.connection();
    conn.setDoOutput(true);
    conn.setRequestMethod(PUT.name());
    if(type != null) conn.setRequestProperty(HttpText.CONTENT_TYPE, type.toString());
    try(OutputStream bos = new BufferedOutputStream(conn.getOutputStream())) {
      if(is != null) {
        // send input stream if it not empty
        try(BufferedInputStream bis = new BufferedInputStream(is)) {
          for(int i; (i = bis.read()) != -1;) bos.write(i);
        }
      }
    }
    try {
      read(conn.getInputStream());
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }