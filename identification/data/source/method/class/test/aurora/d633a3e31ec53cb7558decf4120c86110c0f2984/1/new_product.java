@Override
  public synchronized int changeState(
      TaskQuery query,
      ScheduleStatus newState,
      Optional<String> auditMessage) {

    return changeState(query, stateUpdaterWithAuditMessage(newState, auditMessage));
  }