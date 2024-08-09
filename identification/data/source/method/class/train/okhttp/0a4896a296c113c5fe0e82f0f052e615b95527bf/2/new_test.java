  @Test public void read() throws Exception {
    write(ByteString.encodeUtf8("Hello, World"));

    FileOperator operator = new FileOperator(randomAccessFile.getChannel());

    Buffer buffer = new Buffer();
    operator.read(0, buffer, 5);
    assertThat(buffer.readUtf8()).isEqualTo("Hello");

    operator.read(4, buffer, 5);
    assertThat(buffer.readUtf8()).isEqualTo("o, Wo");
  }