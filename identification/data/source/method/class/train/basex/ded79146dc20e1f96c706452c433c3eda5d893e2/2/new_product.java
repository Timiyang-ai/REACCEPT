protected static MediaType contentType(final String query) throws IOException {
    final IOUrl url = new IOUrl(REST_ROOT + query);
    final HttpURLConnection conn = (HttpURLConnection) url.connection();
    try {
      read(conn.getInputStream());
      return new MediaType(conn.getContentType());
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }