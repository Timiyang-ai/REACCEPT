  @Test public void writeList_startsWithSpanKeyAndLengthPrefix() {
    byte[] bytes = writer.writeList(asList(CLIENT_SPAN));

    assertThat(bytes)
      .hasSize(writer.sizeInBytes(CLIENT_SPAN))
      .startsWith((byte) 10, SPAN.sizeOfValue(CLIENT_SPAN));
  }