private String put(final String query, final InputStream is)
      throws IOException {

    final URL url = new URL(ROOT + query);
    final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setDoOutput(true);
    conn.setRequestMethod("PUT");
    final OutputStream bos = new BufferedOutputStream(conn.getOutputStream());
    if(is != null) {
      // send input stream if it not empty
      final BufferedInputStream bis = new BufferedInputStream(is);
      for(int i; (i = bis.read()) != -1;) bos.write(i);
      bis.close();
      bos.close();
    }
    try {
      return read(conn.getInputStream());
    } catch(final IOException ex) {
      throw new IOException(read(conn.getErrorStream()));
    } finally {
      conn.disconnect();
    }
  }