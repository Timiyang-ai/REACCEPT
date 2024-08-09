  @Test(dataProvider = "buffers")
  public void drain(ReadBuffer<Boolean> buffer) {
    for (int i = 0; i < 2 * ReadBuffer.BUFFER_SIZE; i++) {
      buffer.offer(Boolean.TRUE);
    }
    buffer.drain();
    int drained = buffer.drained();
    int recorded = buffer.recorded();
    assertThat(drained, is(recorded));
  }