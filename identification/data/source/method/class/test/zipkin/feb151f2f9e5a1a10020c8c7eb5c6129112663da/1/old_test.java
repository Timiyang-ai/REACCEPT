@Test public void write_startsWithSpanKeyAndLengthPrefix() {
    byte[] buff = writer.write(CLIENT_SPAN);

    assertThat(buff)
      .hasSize(writer.sizeInBytes(CLIENT_SPAN))
      .startsWith((byte) 10, SPAN.sizeOfValue(CLIENT_SPAN));
  }