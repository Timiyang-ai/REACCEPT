public void apply(DropColumn dropColumn) {
    checkTableName(dropColumn.getTableName());
    columns.remove(dropColumn.getColumnName());
  }