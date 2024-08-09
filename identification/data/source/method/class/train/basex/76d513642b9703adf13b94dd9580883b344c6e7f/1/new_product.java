private static String get(final String query) throws IOException {
    final URL url = new URL(ROOT + query);

    // create connection
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      final BufferInput bi = new BufferInput(conn.getInputStream());
      return read(bi).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }