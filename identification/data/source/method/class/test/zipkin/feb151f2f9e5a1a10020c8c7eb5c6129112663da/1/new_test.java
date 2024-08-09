@Test public void write_startsWithSpanKeyAndLengthPrefix() {
    byte[] bytes = writer.write(CLIENT_SPAN);

    assertThat(bytes)
      .hasSize(writer.sizeInBytes(CLIENT_SPAN))
      .startsWith((byte) 10, SPAN.sizeOfValue(CLIENT_SPAN));
  }