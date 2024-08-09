  private String toSql(RelNode root) {
    return toSql(root, SqlDialect.DatabaseProduct.CALCITE.getDialect());
  }