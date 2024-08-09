private HttpURLConnection connect(final String url, final HttpRequest request)
      throws QueryException, IOException {

    final HttpURLConnection conn = connection(url, request);
    if(request != null) {
      // HTTP Basic Authentication
      final byte[] sendAuth = request.attrs.get(SEND_AUTHORIZATION);
      if(sendAuth != null && Bln.parse(sendAuth, info)) {
        final String user = string(request.attrs.get(USERNAME));
        final String pass = string(request.attrs.get(PASSWORD));
        conn.setRequestProperty(AUTHORIZATION,
            BASIC + ' ' + org.basex.util.Base64.encode(user + ':' + pass));
      }
    }

    return conn;
  }