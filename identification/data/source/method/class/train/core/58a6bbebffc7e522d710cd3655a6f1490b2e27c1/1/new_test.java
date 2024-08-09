@Test(groups = {"observerMethod"})
   @SpecAssertion(section = "7.6")
   public void testObserve()
   {
      //Create a test annotation for the event and use it to construct the
      //event object
      Annotation[] annotations = new Annotation[] { new TameAnnotationLiteral() };
      EventImpl<DangerCall> eventComponent = new EventImpl<DangerCall>(manager, DangerCall.class, annotations);
      Observer<DangerCall> observer = new AnObserver<DangerCall>();
      eventComponent.observe(observer, new SynchronousAnnotationLiteral());
      assert manager.getObservedEventType().equals(DangerCall.class);

       //Try duplicate annotation bindings
      boolean duplicateDetected = false;
      try
      {
         eventComponent.observe(observer,
               new TameAnnotationLiteral());
      } catch (DuplicateBindingTypeException e)
      {
         duplicateDetected = true;
      }
      assert duplicateDetected;

      //Try an invalid binding type
      boolean nonBindingTypeDetected = false;
      try
      {
         eventComponent.observe(observer,
               new RiverFishStereotypeAnnotationLiteral());
      } catch (IllegalArgumentException e)
      {
         nonBindingTypeDetected = true;
      }
      assert nonBindingTypeDetected;
   }