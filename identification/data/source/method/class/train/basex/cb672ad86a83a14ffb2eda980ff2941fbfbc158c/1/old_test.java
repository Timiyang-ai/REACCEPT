@Test
  public void query() throws BaseXException {
    final ClientQuery cq = cs.query("1");
    if(!cq.more()) fail("No result returned");
    assertEquals("1", cq.next());
    assertEquals("", cq.close());
  }