@Test(groups = "eventbus")
   @SpecAssertion(section = "7.7.1")
   public void testIsObserverInterested()
   {
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      EventObserver<DangerCall> wrappedObserver = new EventObserver<DangerCall>(observer, DangerCall.class, new TameAnnotationLiteral());
      assert wrappedObserver.getEventBindings().length == 1;
      assert wrappedObserver.isObserverInterested(new TameAnnotationLiteral());
      assert !wrappedObserver.isObserverInterested(new AnimalStereotypeAnnotationLiteral());
      assert !wrappedObserver.isObserverInterested();
      assert wrappedObserver.isObserverInterested(new TameAnnotationLiteral(), new RoleBinding("Admin"));
      
      // Perform some tests with binding values (7.7.1)
      wrappedObserver = new EventObserver<DangerCall>(observer, DangerCall.class, new RoleBinding("Admin"));
      assert wrappedObserver.getEventBindings().length == 1;
      assert wrappedObserver.isObserverInterested(new RoleBinding("Admin"));
      assert !wrappedObserver.isObserverInterested(new RoleBinding("User"));
   }