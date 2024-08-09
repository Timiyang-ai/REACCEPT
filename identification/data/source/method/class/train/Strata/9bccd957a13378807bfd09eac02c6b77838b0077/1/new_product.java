public static CharSource toCharSource(ByteSource byteSource) {
    return new CharSource() {

      @Override
      public Reader openStream() throws IOException {
        return toReader(byteSource.openStream());
      }

      @Override
      public String toString() {
        return "UnicodeBom.toCharSource(" + byteSource.toString() + ")";
      }
    };
  }