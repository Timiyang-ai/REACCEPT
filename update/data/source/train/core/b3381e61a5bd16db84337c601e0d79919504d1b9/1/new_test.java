@Test(groups = "event")
   @SpecAssertion(section = "7.7.1")
   public void testIsObserverInterested()
   {
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      EventObserver<DangerCall> wrappedObserver = new EventObserver<DangerCall>(observer, DangerCall.class, new TameAnnotationLiteral());
      assert wrappedObserver.getEventBindings().size() == 1;
      assert wrappedObserver.isObserverInterested(new MetaDataCache(), new TameAnnotationLiteral());
      assert !wrappedObserver.isObserverInterested(new MetaDataCache(), new AnimalStereotypeAnnotationLiteral());
      assert !wrappedObserver.isObserverInterested(new MetaDataCache());
      assert wrappedObserver.isObserverInterested(new MetaDataCache(), new TameAnnotationLiteral(), new RoleBinding("Admin"));
      
      // Perform some tests with binding values (7.7.1)
      wrappedObserver = new EventObserver<DangerCall>(observer, DangerCall.class, new RoleBinding("Admin"));
      assert wrappedObserver.getEventBindings().size() == 1;
      assert wrappedObserver.isObserverInterested(new MetaDataCache(), new RoleBinding("Admin"));
      assert !wrappedObserver.isObserverInterested(new MetaDataCache(), new RoleBinding("User"));
   }