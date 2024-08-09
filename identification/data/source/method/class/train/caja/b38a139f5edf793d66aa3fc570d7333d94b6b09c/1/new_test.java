  private void addFact(String expr, String value) throws ParseException {
    kb.addFact(jsExpr(fromString(expr)),
               Fact.is((Literal) jsExpr(fromString(value)).fold(false)));
  }