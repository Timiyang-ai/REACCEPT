@Test
  public void stripNS() throws Exception {
    final IO io = IO.get("<a xmlns:a='a'><b><c/><c/><c/></b></a>");
    final ANode root = new DBNode(io, CONTEXT.prop);
    final QueryProcessor qp = new QueryProcessor("/*:a/*:b", root, CONTEXT);
    final ANode sub = (ANode) qp.iter().next();
    DataBuilder.stripNS(sub, token("a"), CONTEXT);
  }