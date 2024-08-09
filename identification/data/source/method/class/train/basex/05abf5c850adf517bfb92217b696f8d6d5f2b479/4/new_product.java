static String post(final String query, final String request,
                     final String type) throws IOException {

    // create connection
    final URL url = new URL(root + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod(POST.name());
    conn.setRequestProperty(MimeTypes.CONTENT_TYPE, type);
    // basic authentication
    final String encoded = Base64.encode(Text.ADMIN + ':' + Text.ADMIN);
    conn.setRequestProperty(HTTPText.AUTHORIZATION, HTTPText.BASIC + ' ' + encoded);
    // send query
    final OutputStream out = conn.getOutputStream();
    out.write(token(request));
    out.close();

    try {
      return read(conn.getInputStream()).replaceAll("\r?\n *", "");
    } catch(final IOException ex) {
      throw error(conn, ex);
    } finally {
      conn.disconnect();
    }
  }