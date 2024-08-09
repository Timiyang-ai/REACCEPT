public void set(int index, String value) throws IOException {
      Writer writer = null;
      try {
        writer = new OutputStreamWriter(newOutputStream(index), Util.UTF_8);
        writer.write(value);
      } finally {
        Util.closeQuietly(writer);
      }
    }