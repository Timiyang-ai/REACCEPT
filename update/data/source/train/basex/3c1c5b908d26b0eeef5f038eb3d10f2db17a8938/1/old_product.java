String get(final String query) throws IOException {
    final URL url = new URL(ROOT + "?" + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    final int code = conn.getResponseCode();
    final InputStream is = conn.getInputStream();
    final ByteArrayOutputStream bais = new ByteArrayOutputStream();
    int i;
    while((i = is.read()) != -1) bais.write(i);
    is.close();
    conn.disconnect();
    final String result = bais.toString().replaceAll("\r?\n *", "");
    if(code != HttpURLConnection.HTTP_OK) {
      System.out.println("???????????");
      throw new JaxRxException(code, result);
    }
    return result;
  }