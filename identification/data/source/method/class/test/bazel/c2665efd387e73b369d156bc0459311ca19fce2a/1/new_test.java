  @Test
  public void reset() throws Exception {
    byte[] data = new byte[]{1, 2, 3};
    Chunker chunker = Chunker.builder().setInput(data).setChunkSize(1).build();

    assertNextEquals(chunker, (byte) 1);
    assertNextEquals(chunker, (byte) 2);

    chunker.reset();

    assertNextEquals(chunker, (byte) 1);
    assertNextEquals(chunker, (byte) 2);
    assertNextEquals(chunker, (byte) 3);

    chunker.reset();

    assertNextEquals(chunker, (byte) 1);
  }