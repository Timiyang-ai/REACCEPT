protected static String post(final String query, final String request, final MediaType type)
      throws IOException {

    // create connection
    final IOUrl url = new IOUrl(root + query);
    final HttpURLConnection conn = (HttpURLConnection) url.connection();
    conn.setDoOutput(true);
    conn.setRequestMethod(POST.name());
    conn.setRequestProperty(HttpText.CONTENT_TYPE, type.toString());
    // basic authentication
    final String encoded = org.basex.util.Base64.encode(ADMIN + ':' + ADMIN);
    conn.setRequestProperty(HttpText.AUTHORIZATION, AuthMethod.BASIC + " " + encoded);
    // send query
    try(final OutputStream out = conn.getOutputStream()) {
      out.write(token(request));
    }

    try {
      return read(conn.getInputStream());
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }