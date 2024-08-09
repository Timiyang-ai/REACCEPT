public void registerPendingHistoryDropColumns(ModelContainer newModel) {

    for (Entry entry : map.values()) {
      for (ChangeSet changeSet : entry.list) {
        for (Object change : changeSet.getChangeSetChildren()) {
          if (change instanceof DropColumn) {
            DropColumn dropColumn = (DropColumn) change;
            if (Boolean.TRUE.equals(dropColumn.isWithHistory())) {
              newModel.registerPendingDropColumn(dropColumn);
            }
          }
        }
      }
    }
  }