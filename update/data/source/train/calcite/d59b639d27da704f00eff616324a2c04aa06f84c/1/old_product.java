public static RexSubQuery exists(RelNode rel) {
    final RelDataTypeFactory typeFactory = rel.getCluster().getTypeFactory();
    final RelDataType type = typeFactory.createSqlType(SqlTypeName.BOOLEAN);
    return new RexSubQuery(type, SqlStdOperatorTable.EXISTS,
        ImmutableList.<RexNode>of(), rel);
  }