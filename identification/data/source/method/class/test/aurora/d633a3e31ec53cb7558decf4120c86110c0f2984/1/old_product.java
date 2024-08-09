synchronized int changeState(
      TaskQuery query,
      ScheduleStatus newState,
      @Nullable String auditMessage) {

    return changeState(query, stateUpdaterWithAuditMessage(newState, auditMessage));
  }