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

    compareColumns(modelDiff, newTable);

    if (MColumn.different(comment, newTable.comment)) {
      AddTableComment addTableComment = new AddTableComment();
      addTableComment.setName(name);
      if (newTable.comment == null) {
        addTableComment.setComment(DdlHelp.DROP_COMMENT);
      } else {
        addTableComment.setComment(newTable.comment);
      }
      modelDiff.addTableComment(addTableComment);
    }


    compareCompoundKeys(modelDiff, newTable);
    compareUniqueKeys(modelDiff, newTable);
  }