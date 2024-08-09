@Test
  public void testDelete() {

    FNFile fnFile = new FNFile();
    Expr[] expr = new Expr[1];
    expr[0] = Str.get("test2");
    fnFile.init(FunDef.DELETE, expr);
    try {
      fnFile.atomic(null);
    } catch(QueryException e) {
      throw new RuntimeException(e);
    }

  }