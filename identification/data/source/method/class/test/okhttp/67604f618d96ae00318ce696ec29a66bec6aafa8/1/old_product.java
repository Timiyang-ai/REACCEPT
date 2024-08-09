public void set(int index, String value) throws IOException {
      BufferedSink writer = null;
      try {
        writer = Okio.buffer(Okio.sink(newOutputStream(index)));
        writer.writeUtf8(value);
      } finally {
        Util.closeQuietly(writer);
      }
    }