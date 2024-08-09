@Test
  public void baseUri() throws Exception {
    final String find = "base-uri(collection('" + NAME + '/' + DIR + "xmark.xml'))";
    final QueryProcessor qp = new QueryProcessor(find, context);
    try {
      assertEquals(NAME + '/' + FILES[1], qp.execute().toString());
    } finally {
      qp.close();
    }
  }