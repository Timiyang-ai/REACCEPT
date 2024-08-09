@VisibleForTesting
  int changeState(TaskQuery query, ScheduleStatus newState) {
    return changeState(query, stateUpdater(newState));
  }