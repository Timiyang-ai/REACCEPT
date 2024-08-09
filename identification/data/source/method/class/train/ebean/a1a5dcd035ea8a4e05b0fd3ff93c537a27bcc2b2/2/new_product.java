public void apply(DropColumn dropColumn) {
    checkTableName(dropColumn.getTableName());
    MColumn removed = columns.remove(dropColumn.getColumnName());
    if (removed == null) {
      throw new IllegalStateException("Column [" + dropColumn.getColumnName() + "] does not exist for DropColumn change on table [" + dropColumn.getTableName() + "]?");
    }
  }