static byte[] gzip(byte[] data) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length);
    try (OutputStream out = new GZIPOutputStream(baos)) {
      out.write(data);
    }
    return baos.toByteArray();
  }