public void compareTo(ModelContainer newModel) {

    Map<String, MTable> newTables = newModel.getTables();
    for (MTable newTable : newTables.values()) {

      MTable currentTable = baseModel.getTable(newTable.getName());
      if (currentTable == null) {
        addNewTable(newTable);
      } else {
        compareTables(currentTable, newTable);
      }
    }

    // search for tables that are no longer used
    for (MTable existingTable : baseModel.getTables().values()) {
      if (!newTables.containsKey(existingTable.getName())) {
        addDropTable(existingTable);
      }
    }

    Map<String, MIndex> newIndexes = newModel.getIndexes();
    for (MIndex newIndex : newIndexes.values()) {
      MIndex currentIndex = baseModel.getIndex(newIndex.getIndexName());
      if (currentIndex == null) {
        addCreateIndex(newIndex.createIndex());
      } else {
        compareIndexes(currentIndex, newIndex);
      }
    }

    // search for indexes that are no longer used
    for (MIndex existingIndex : baseModel.getIndexes().values()) {
      if (!newIndexes.containsKey(existingIndex.getIndexName())) {
        addDropIndex(existingIndex.dropIndex());
      }
    }

  }