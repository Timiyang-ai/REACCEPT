@Test
  public void set() {
    final BitArray a = new BitArray();
    a.set(128);
    assertTrue("Bit 128 is 0", a.get(128));
    a.init();
    a.set(129);
    assertTrue("Bit 129 is 0", a.get(129));
  }