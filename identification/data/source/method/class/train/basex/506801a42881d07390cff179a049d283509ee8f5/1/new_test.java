@Test
  public void testSize() throws Exception {
    assertEquals("Unexpected size!", size, data.meta.size);
    reload();
    assertEquals("Unexpected size!", size, data.meta.size);
  }