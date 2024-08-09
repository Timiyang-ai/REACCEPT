@Test public void testEmpty() throws Exception {
    that()
        .with(CalciteAssert.Config.JDBC_FOODMART)
        .doWithConnection(
            new Function<CalciteConnection, Object>() {
              public Object apply(CalciteConnection a0) {
                try {
                  ResultSet rset =
                      a0.getMetaData().getPrimaryKeys(
                          null, null, "sales_fact_1997");
                  assertFalse(rset.next());
                } catch (SQLException e) {
                  throw new RuntimeException(e);
                }
                return null;
              }
            });
  }