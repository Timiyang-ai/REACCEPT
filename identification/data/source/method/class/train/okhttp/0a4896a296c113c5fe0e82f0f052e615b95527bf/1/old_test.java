  @Test public void write() throws Exception {
    FileOperator operator = new FileOperator(randomAccessFile.getChannel());

    Buffer buffer1 = new Buffer().writeUtf8("Hello, World");
    operator.write(0, buffer1, 5);
    assertThat(buffer1.readUtf8()).isEqualTo(", World");

    Buffer buffer2 = new Buffer().writeUtf8("icopter!");
    operator.write(3, buffer2, 7);
    assertThat(buffer2.readUtf8()).isEqualTo("!");

    assertThat(snapshot()).isEqualTo(ByteString.encodeUtf8("Helicopter"));
  }