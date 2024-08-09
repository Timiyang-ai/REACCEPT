  @Test
  public void parseMemoryLine() {
    final String line = "MemFree:        500 kB";
    final long size = this.util.parseMemoryLine(line);
    assertEquals(500, size);
  }