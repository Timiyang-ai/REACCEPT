public void set(int index, String value) throws IOException {
      BufferedSink writer = Okio.buffer(newSink(index));
      writer.writeUtf8(value);
      writer.close();
    }