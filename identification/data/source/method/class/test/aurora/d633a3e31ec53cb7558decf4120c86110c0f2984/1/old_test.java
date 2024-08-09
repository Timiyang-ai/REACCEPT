  private StateChangeResult changeState(
      String taskId,
      Optional<ScheduleStatus> casState,
      ScheduleStatus newState,
      Optional<String> auditMessage) {

    return storage.write(storeProvider -> stateManager.changeState(
        storeProvider,
        taskId,
        casState,
        newState,
        auditMessage));
  }