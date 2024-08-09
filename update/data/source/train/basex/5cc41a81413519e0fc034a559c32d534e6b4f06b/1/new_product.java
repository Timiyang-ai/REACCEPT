private HttpURLConnection connect(final String url, final HttpRequest request)
      throws QueryException, IOException {

    HttpURLConnection conn = connection(url, request);
    final String user = request.attribute(USERNAME), pass = request.attribute(PASSWORD);
    if(user != null) {
      if(request.authMethod == AuthMethod.BASIC) {
        conn.setRequestProperty(AUTHORIZATION, BASIC + ' ' +
            org.basex.util.Base64.encode(user + ':' + pass));

      } else {
        conn.setRequestProperty(AUTHORIZATION, DIGEST);

        final EnumMap<Request, String> map = digestHeaders(conn.getHeaderField(WWW_AUTHENTICATE));
        final String realm = map.get(REALM), nonce = map.get(NONCE),
          qop = map.get(QOP), opaque = map.get(OPAQUE),
          cnonce = Strings.md5(Long.toString(System.nanoTime())),
          nc = "00000001", uri = conn.getURL().getPath(),
          ha1 = Strings.md5(user + ':' + realm + ':' + pass),
          ha2 = Strings.md5(request.attribute(METHOD) + ':' + uri),
          rsp = Strings.md5(ha1 + ':' + nonce + ':' + nc + ':' + cnonce + ':' + qop + ':' + ha2),
          creds = USERNAME + "=" + user + ',' + REALM + '=' + realm + ',' + NONCE + '=' + nonce
            + ',' + URI + '=' + uri + ',' + QOP + '=' + qop + ',' + NC + '=' + nc + ',' + CNONCE
            + '=' + cnonce + ',' + RESPONSE + '=' + rsp + ',' + OPAQUE + '=' + opaque;

        conn.disconnect();
        conn = connection(url, request);
        conn.setRequestProperty(AUTHORIZATION, DIGEST + ' ' + creds);
      }
    }
    return conn;
  }