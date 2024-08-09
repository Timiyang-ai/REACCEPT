@Test
  public void query() throws IOException {
    final Query query = session.query("1");
    check("1", query.execute());
  }