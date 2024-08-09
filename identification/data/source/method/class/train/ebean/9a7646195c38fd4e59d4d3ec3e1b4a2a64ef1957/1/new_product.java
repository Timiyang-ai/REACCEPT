public DropTable dropTable() {
    DropTable dropTable = new DropTable();
    dropTable.setName(name);
    // we must add pk col name & sequence name, as we have to delete the sequence also.
    if (identityType != IdentityType.GENERATOR && identityType != IdentityType.EXTERNAL) {
      String pkCol = null;
      for (MColumn column : columns.values()) {
        if (column.isPrimaryKey()) {
          if (pkCol == null) {
            pkCol = column.getName();
          } else { // multiple pk cols -> no sequence
            pkCol = null;
            break;
          }
        }
      }
      if (pkCol != null) {
        dropTable.setSequenceCol(pkCol);
        dropTable.setSequenceName(sequenceName);
      }
    }
    return dropTable;
  }