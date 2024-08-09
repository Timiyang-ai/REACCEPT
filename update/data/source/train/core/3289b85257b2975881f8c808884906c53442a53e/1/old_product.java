private <T> void deferEvent(T event, Observer<T> observer)
   {
      TransactionListener transactionListener = manager.getInstanceByType(TransactionListener.class);
      DeferredEventNotification<T> deferredEvent = new DeferredEventNotification<T>(event, observer);
      transactionListener.registerSynhronization(deferredEvent);
   }