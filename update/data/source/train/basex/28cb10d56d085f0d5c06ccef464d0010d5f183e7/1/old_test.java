@Test
  public void baseUri() throws Exception {
    final String find =
      "for $x in collection('" + NAME + '/' + DIR + "xmark.xml') " +
      "return base-uri($x)";
    final QueryProcessor qp = new QueryProcessor(find, context);
    assertEquals(NAME + '/' + FILES[1], qp.iter().next().toJava());
    qp.close();
  }