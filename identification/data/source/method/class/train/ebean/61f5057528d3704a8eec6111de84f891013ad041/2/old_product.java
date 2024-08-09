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

    Map<String, MColumn> newColumnMap = newTable.getColumns();

    // compare newColumns to existing columns (look for new and diff columns)
    for (MColumn newColumn : newColumnMap.values()) {
      MColumn localColumn = getColumn(newColumn.getName());
      if (localColumn == null) {
        // can ignore if draftOnly column and non-draft table
        if (!newColumn.isDraftOnly() || draft) {
          diffNewColumn(newColumn);
        }
      } else {
        localColumn.compare(modelDiff, this, newColumn);
      }
    }

    // compare existing columns (look for dropped columns)
    int columnPosition = 0;
    for (MColumn existingColumn : allColumns()) {
      MColumn newColumn = newColumnMap.get(existingColumn.getName());
      if (newColumn == null) {
        diffDropColumn(modelDiff, existingColumn, columnPosition, newTable);
      } else if (newColumn.isDraftOnly() && !draft) {
        // effectively a drop column (draft only column on a non-draft table)
        logger.trace("... drop column {} from table {} as now draftOnly", newColumn.getName(), name);
        diffDropColumn(modelDiff, existingColumn, columnPosition, newTable);
      }
      columnPosition++;
    }

    if (addColumn != null) {
      modelDiff.addAddColumn(addColumn);
    }

    if (MColumn.different(comment, newTable.comment)) {
      AddTableComment addTableComment = new AddTableComment();
      addTableComment.setName(name);
      addTableComment.setComment(newTable.comment);
      modelDiff.addTableComment(addTableComment);
    }
  }