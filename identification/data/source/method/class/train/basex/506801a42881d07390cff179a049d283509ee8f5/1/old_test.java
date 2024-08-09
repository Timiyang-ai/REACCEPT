@Test
  public void testSize() throws Exception {
    assertEquals("Unexpected size!", size, data.size);
    reload();
    assertEquals("Unexpected size!", size, data.size);
  }