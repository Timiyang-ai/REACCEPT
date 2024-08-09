private void writeInputStreamToFile(InputStream inputStream, File audio) {
    OutputStream outStream = null;
    try {
      outStream = new FileOutputStream(audio);

      byte[] buffer = new byte[8 * 1024];
      int bytesRead;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
      }
    } catch (Exception e) {
      fail();
    } finally {
      try {
        inputStream.close();
        outStream.close();
      } catch (Exception e) {
        fail();
      }
    }
  }