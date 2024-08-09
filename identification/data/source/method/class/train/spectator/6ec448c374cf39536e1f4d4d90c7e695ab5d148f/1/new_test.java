  @Test
  public void dataExpr() {
    Subscription sub = new Subscription().withExpression(":true,:sum");
    Assertions.assertEquals(new DataExpr.Sum(Query.TRUE), sub.dataExpr());
  }