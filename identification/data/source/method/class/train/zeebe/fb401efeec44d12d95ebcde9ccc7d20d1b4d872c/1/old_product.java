public boolean triggerEvent(
      long eventScopeKey, long eventKey, DirectBuffer elementId, DirectBuffer payload) {
    eventScopeInstanceKey.wrapLong(eventScopeKey);
    final EventScopeInstance instance = eventScopeInstanceColumnFamily.get(eventScopeInstanceKey);

    if (instance != null && instance.isAccepting()) {
      if (instance.isInterrupting(elementId)) {
        instance.setAccepting(false);
        eventScopeInstanceColumnFamily.put(eventScopeInstanceKey, instance);
      }

      createTrigger(eventScopeKey, eventKey, elementId, payload);

      return true;
    } else {
      return false;
    }
  }