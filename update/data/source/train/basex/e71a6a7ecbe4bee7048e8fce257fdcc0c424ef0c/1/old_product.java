protected static String get(final String root, final String query) throws IOException {
    final URL url = new URL(root + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      return read(new BufferInput(conn.getInputStream())).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }