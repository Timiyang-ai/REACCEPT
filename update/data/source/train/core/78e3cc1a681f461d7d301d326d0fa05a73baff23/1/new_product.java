public void fireEvent(Object event, Annotation... bindings)
   {
      // Check the event object for template parameters which are not allowed by
      // the spec.
      if (Reflections.isParameterizedType(event.getClass()))
      {
         throw new IllegalArgumentException("Event type " + event.getClass().getName() + " is not allowed because it is a generic");
      }
      // Get the observers for this event. Although resolveObservers is
      // parameterized, this
      // method is not, so we have to use Observer<Object> for observers.
      Set<Observer<Object>> observers = this.resolveObservers(event, bindings);
      this.eventManager.notifyObservers(observers, event);
   }