private void synthesize(String text, File audio) {
    final InputStream is = service.synthesize(text, Voice.EN_LISA, HttpMediaType.AUDIO_WAV);
    Assert.assertNotNull(is);
    OutputStream outStream = null;
    try {
      outStream = new FileOutputStream(audio);

      final byte[] buffer = new byte[8 * 1024];
      int bytesRead;
      while ((bytesRead = is.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
      }
    } catch (final Exception e) {
      fail();
    } finally {
      IOUtils.closeQuietly(is);
      IOUtils.closeQuietly(outStream);
    }
  }