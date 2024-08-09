@Test(groups = "observerMethod")
   public void testAddObserver()
   {
      EventManager eventManager = new EventManager(manager);
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      eventManager.addObserver(observer, DangerCall.class);
      DangerCall event = new DangerCall();

      Set<Observer<DangerCall>> observerSet = eventManager.getObservers(event);
      assert observerSet.size() == 1;
      assert observerSet.iterator().next().equals(observer);

      // Add another observer for the same event, but with an event binding
      observer = new AnObserver<DangerCall>();
      eventManager.addObserver(observer, DangerCall.class, new TameAnnotationLiteral());
      observerSet = eventManager.getObservers(event);
      assert observerSet.size() == 1;
      observerSet = eventManager.getObservers(event, new TameAnnotationLiteral());
      assert observerSet.size() == 2;
   }