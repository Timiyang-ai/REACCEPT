public <T> void removeObserver(Observer<T> observer, Class<T> eventType, Annotation... bindings)
   {
      List<EventObserver<?>> observers = registeredObservers.get(eventType);
      for (Iterator<EventObserver<?>> i = observers.iterator(); i.hasNext();)
      {
         if (observer.equals(i.next().getObserver()))
         {
            i.remove();
            break;
         }
      }
   }