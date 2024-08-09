@Test
  public void stripNS() throws Exception {
    final IO io = IO.get("<a xmlns:a='a'><b><c/><c/><c/></b></a>");
    final ANode root = new DBNode(io, context.prop);
    final QueryProcessor qp = new QueryProcessor("/*:a/*:b", context).context(root);
    final ANode sub = (ANode) qp.iter().next();
    DataBuilder.stripNS(sub, token("a"), context);
  }