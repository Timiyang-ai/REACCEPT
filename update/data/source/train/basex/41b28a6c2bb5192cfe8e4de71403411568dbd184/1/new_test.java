@Test
  public final void testSize() {
    assertEquals("Unexpected size!", size, CONTEXT.data.meta.size);
    reload();
    assertEquals("Unexpected size!", size, CONTEXT.data.meta.size);
  }