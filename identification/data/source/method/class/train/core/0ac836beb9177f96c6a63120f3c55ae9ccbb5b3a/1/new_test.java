@Test(groups = "eventbus")
   public final void testNotify() throws Exception
   {
      AnObserver observerInstance = new AnObserver();
      Observer<Event> observer = new MockObserverImpl<Event>(tuna, om, Event.class);
      ((MockObserverImpl<Event>) observer).setInstance(observerInstance);
      Event event = new Event();
      observer.notify(manager, event);
      assert observerInstance.notified;
   }