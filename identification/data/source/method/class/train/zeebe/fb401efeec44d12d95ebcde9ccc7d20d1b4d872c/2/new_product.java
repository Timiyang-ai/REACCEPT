public boolean triggerEvent(
      long eventScopeKey, long eventKey, DirectBuffer elementId, DirectBuffer variables) {
    this.eventScopeKey.wrapLong(eventScopeKey);
    final EventScopeInstance instance = eventScopeInstanceColumnFamily.get(this.eventScopeKey);

    if (instance != null && instance.isAccepting()) {
      if (instance.isInterrupting(elementId)) {
        instance.setAccepting(false);
        eventScopeInstanceColumnFamily.put(this.eventScopeKey, instance);
      }

      createTrigger(eventScopeKey, eventKey, elementId, variables);

      return true;
    } else {
      return false;
    }
  }