@Test
  public void testSize() {
    assertEquals("Unexpected size!", size, data.meta.size);
    reload();
    assertEquals("Unexpected size!", size, data.meta.size);
  }