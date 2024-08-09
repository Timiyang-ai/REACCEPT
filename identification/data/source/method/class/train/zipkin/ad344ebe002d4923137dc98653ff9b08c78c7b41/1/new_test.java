  @Test public void readLong_bytes() {
    byte[] bytes = {
      (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
      (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
    };

    ReadBuffer readBuffer = ReadBuffer.wrap(bytes);

    assertThat(readBuffer.readLong())
      .isEqualTo(0x0102030405060708L);
    assertThat(readBuffer.available()).isZero();
  }