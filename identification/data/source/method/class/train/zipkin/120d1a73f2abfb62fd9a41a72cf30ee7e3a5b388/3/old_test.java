@Test public void writeVarint_64() {
    long number = 300;

    Buffer buffer = new Buffer(Buffer.varintSizeInBytes(number));
    buffer.writeVarint(number);

    assertThat(buffer.toByteArray())
      .containsExactly(0b1010_1100, 0b0000_0010);
  }