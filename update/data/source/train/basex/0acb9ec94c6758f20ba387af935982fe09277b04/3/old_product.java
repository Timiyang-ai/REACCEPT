protected static String post(final String query, final String request, final String type)
      throws IOException {

    // create connection
    final URL url = new URL(root + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod(POST.name());
    conn.setRequestProperty(HttpText.CONTENT_TYPE, type);
    // basic authentication
    final String encoded = org.basex.util.Base64.encode(ADMIN + ':' + ADMIN);
    conn.setRequestProperty(HttpText.AUTHORIZATION, AuthMethod.BASIC + " " + encoded);
    // send query
    try(final OutputStream out = conn.getOutputStream()) {
      out.write(token(request));
    }

    try {
      return read(conn.getInputStream()).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }