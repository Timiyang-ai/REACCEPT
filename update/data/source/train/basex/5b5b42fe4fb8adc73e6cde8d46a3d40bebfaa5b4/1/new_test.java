@Test
  public void set() {
    final BitArray ba = new BitArray();
    ba.set(128);
    assertTrue("Bit 128 is 0", ba.get(128));
    ba.init();
    ba.set(129);
    assertTrue("Bit 129 is 0", ba.get(129));
  }