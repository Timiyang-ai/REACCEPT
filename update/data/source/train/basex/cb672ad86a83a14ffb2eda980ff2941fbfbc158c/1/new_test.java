@Test
  public void query() throws BaseXException {
    final ClientQuery cq = cs.query("1");
    assertEquals("1", cq.execute());
    assertEquals("", cq.close());
  }