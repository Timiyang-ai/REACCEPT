public void set(int index, String value) throws IOException {
      BufferedSink writer = Okio.buffer(Okio.sink(newOutputStream(index)));
      writer.writeUtf8(value);
      writer.close();
    }