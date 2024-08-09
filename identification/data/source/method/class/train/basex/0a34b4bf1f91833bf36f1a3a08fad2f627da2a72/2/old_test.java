@Test
  public void query() throws BaseXException {
    final Query query = session.query("1");
    check("1", query.execute());
    check("", query.close());
  }