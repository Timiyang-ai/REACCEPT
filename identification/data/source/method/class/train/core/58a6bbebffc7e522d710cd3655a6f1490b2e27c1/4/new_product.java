public <T> void addObserver(Observer<T> observer, Class<T> eventType, Annotation... bindings)
   {
      EventObserver<T> eventObserver = new EventObserver<T>(observer, eventType, bindings);
      registeredObservers.put(eventType, eventObserver);
      log.debug("Added observer " + observer + " observing event type " + eventType);
   }