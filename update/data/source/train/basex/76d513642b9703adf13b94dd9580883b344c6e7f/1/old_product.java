private static String get(final String query) throws IOException {
    final URL url = new URL(ROOT + query);

    // create connection
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      return read(conn.getInputStream()).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }