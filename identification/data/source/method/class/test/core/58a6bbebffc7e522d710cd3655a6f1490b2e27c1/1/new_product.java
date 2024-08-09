public <T> void removeObserver(Observer<T> observer, Class<T> eventType, Annotation... bindings)
   {
      List<EventObserver<?>> observers = registeredObservers.get(eventType);
      EventObserver<T> eventObserver = new EventObserver<T>(observer, eventType, manager, bindings);
      observers.remove(eventObserver);
   }