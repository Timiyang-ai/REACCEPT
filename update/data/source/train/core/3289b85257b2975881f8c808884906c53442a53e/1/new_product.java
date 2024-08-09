private <T> void deferEvent(T event, Observer<T> observer)
   {
      DeferredEventNotification<T> deferredEvent = new DeferredEventNotification<T>(event, observer);
      userTransaction.registerSynchronization(deferredEvent);
   }