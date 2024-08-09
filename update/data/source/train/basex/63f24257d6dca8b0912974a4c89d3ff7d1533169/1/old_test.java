@Test public void nextFree() {
    final BitArray ba = new BitArray();
    ba.init(new long[] {-1L, 0L}, 64);
    assertEquals("Incorrect next clear bit", 64, ba.nextFree(0));
  }