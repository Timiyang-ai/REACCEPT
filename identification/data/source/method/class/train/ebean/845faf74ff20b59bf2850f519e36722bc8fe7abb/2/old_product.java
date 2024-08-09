public void compareTo(ModelContainer newModel) {

    for (MTable newTable : newModel.getTables().values()) {

      MTable currentTable = baseModel.getTable(newTable.getName());
      if (currentTable == null) {
        addNewTable(newTable);
      } else {
        compareTables(currentTable, newTable);
      }
    }

    //TODO: other parts of the model? views, indexes etc

  }