public void registerPendingHistoryDropColumns(ModelContainer newModel) {

    for (Entry entry : map.values()) {
      for (ChangeSet changeSet : entry.list) {
        newModel.registerPendingHistoryDropColumns(changeSet);
      }
    }
  }