@Test
  public void stripNS() throws Exception {
    final IO io = IO.get("<a xmlns:a='a'><b><c/><c/><c/></b></a>");
    try(QueryProcessor qp = new QueryProcessor("/*:a/*:b", context).context(new DBNode(io))) {
      final ANode sub = (ANode) qp.iter().next();
      DataBuilder.stripNS(sub, token("a"), context);
    }
  }