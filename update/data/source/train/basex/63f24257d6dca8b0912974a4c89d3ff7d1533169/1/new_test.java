@Test public void nextFree() {
    final BitArray ba = new BitArray(new long[] { -1L, 0L }, 64);
    assertEquals("Incorrect next clear bit", 64, ba.nextFree());
  }