public void apply(AlterColumn alterColumn) {
    checkTableName(alterColumn.getTableName());
    String columnName = alterColumn.getColumnName();
    MColumn existingColumn = getColumn(columnName);
    if (existingColumn == null) {
      throw new IllegalStateException("Column [" + columnName + "] does not exist for AlterColumn change?");
    }
    existingColumn.apply(alterColumn);
  }