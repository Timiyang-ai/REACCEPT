@Test(groups = { "observerMethod" })
   public void testRemoveObserver()
   {
      EventManager eventManager = new EventManager();
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      eventManager.addObserver(observer, DangerCall.class);
      eventManager.removeObserver(observer, DangerCall.class);
      // FIXME CopyOnWrite broke remove, have to check later
      assert eventManager.getObservers(new MetaDataCache(), new DangerCall()).isEmpty();
   }