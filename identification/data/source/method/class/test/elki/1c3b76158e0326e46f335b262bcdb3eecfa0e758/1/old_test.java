  private void addWeakChild(Object a) {
    byte[] buf = new byte[1_000_001];
    Arrays.fill(buf, (byte) 42);
    Metadata.hierarchyOf(a).addWeakChild(buf);
    buf = null;
    It<Object> it = Metadata.hierarchyOf(a).iterDescendants();
    assertTrue(it.valid());
    assertNotNull(it.get());
    assertTrue(it.get() instanceof byte[]);
    it.advance();
    assertFalse(it.valid());
    it = null;
  }