@Test(groups = {"observerMethod", "broken"})
   public void testRemoveObserver()
   {
      EventManager eventManager = new EventManager(manager);
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      eventManager.addObserver(observer, DangerCall.class);
      eventManager.removeObserver(observer, DangerCall.class);
      // FIXME CopyOnWrite broke remove, have to check later
      assert eventManager.getObservers(new DangerCall()).isEmpty();
   }