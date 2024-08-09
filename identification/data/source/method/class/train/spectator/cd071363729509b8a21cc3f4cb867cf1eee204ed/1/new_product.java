static byte[] gzip(byte[] data) throws IOException {
    return gzip(data, Deflater.DEFAULT_COMPRESSION);
  }