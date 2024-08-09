final String get(final String query) throws IOException {
    final URL url = new URL(ROOT + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    try {
      return read(conn.getInputStream()).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw new IOException(read(conn.getErrorStream()));
    } finally {
      conn.disconnect();
    }
  }