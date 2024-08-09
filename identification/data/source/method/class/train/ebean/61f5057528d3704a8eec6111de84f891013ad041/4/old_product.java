public void compare(ModelDiff modelDiff, MTable newTable) {

    if (withHistory != newTable.withHistory) {
      if (withHistory) {
        DropHistoryTable dropHistoryTable = new DropHistoryTable();
        dropHistoryTable.setBaseTable(name);
        modelDiff.addDropHistoryTable(dropHistoryTable);

      } else {
        AddHistoryTable addHistoryTable = new AddHistoryTable();
        addHistoryTable.setBaseTable(name);
        modelDiff.addAddHistoryTable(addHistoryTable);
      }
    }

    addColumn = null;

    Set<String> mappedColumns = new LinkedHashSet<String>();

    Collection<MColumn> newColumns = newTable.getColumns().values();
    for (MColumn newColumn : newColumns) {
      MColumn localColumn = columns.get(newColumn.getName());
      if (localColumn == null) {
        diffNewColumn(newColumn);
      } else {
        // note that if there are alter column changes in here then
        // the table withHistory is taken into account
        localColumn.compare(modelDiff, this, newColumn);
        mappedColumns.add(newColumn.getName());
      }
    }

    Collection<MColumn> existingColumns = columns.values();
    for (MColumn existingColumn : existingColumns) {
      if (!mappedColumns.contains(existingColumn.getName())) {
        diffDropColumn(modelDiff, existingColumn);
      }
    }

    if (addColumn != null) {
      modelDiff.addAddColumn(addColumn);
    }
  }