public Result visit(Sort e) {
    final Result x = visitChild(0, e.getInput());
    final Builder builder = x.builder(e, Clause.ORDER_BY);
    List<SqlNode> orderByList = Expressions.list();
    for (RelFieldCollation field : e.getCollation().getFieldCollations()) {
      builder.addOrderItem(orderByList, field);
    }
    builder.setOrderBy(new SqlNodeList(orderByList, POS));
    return builder.result();
  }