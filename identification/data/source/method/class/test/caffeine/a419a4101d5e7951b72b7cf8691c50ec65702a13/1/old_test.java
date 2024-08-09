  @Test(dataProvider = "buffer")
  public void drain(BoundedBuffer<String> buffer) {
    for (int i = 0; i < BoundedBuffer.BUFFER_SIZE; i++) {
      buffer.offer(DUMMY);
    }
    int[] read = new int[1];
    buffer.drainTo(e -> read[0]++);
    assertThat(read[0], is(buffer.reads()));
    assertThat(read[0], is(buffer.writes()));
  }