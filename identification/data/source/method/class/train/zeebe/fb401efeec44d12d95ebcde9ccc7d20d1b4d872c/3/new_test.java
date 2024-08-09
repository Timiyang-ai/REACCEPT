  private boolean triggerEvent(long eventScopeKey, long eventKey, EventTrigger eventTrigger) {
    return state.triggerEvent(
        eventScopeKey, eventKey, eventTrigger.getElementId(), eventTrigger.getVariables());
  }