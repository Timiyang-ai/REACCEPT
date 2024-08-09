@SuppressWarnings("unchecked")
   @Test(groups = "event")
   @SpecAssertion(section = "7.6")
   public void testFireEvent()
   {
      DangerCall anEvent = new DangerCall();
      // Create a test annotation for the event and use it to construct the
      // event object
      Annotation[] annotations = new Annotation[] { new TameAnnotationLiteral() };
      EventImpl<DangerCall> eventComponent = new EventImpl<DangerCall>();
      eventComponent.setEventBindings(annotations);
      eventComponent.setManager(manager);
      eventComponent.fire(anEvent, new SynchronousAnnotationLiteral());
      assert anEvent.equals(manager.getEvent());
      assert Reflections.annotationSetMatches(manager.getEventBindings(),
            Tame.class, Synchronous.class);

      // Test duplicate annotations on the fire method call
      boolean duplicateDetected = false;
      try
      {
         eventComponent.fire(anEvent, new TameAnnotationLiteral(),
               new TameAnnotationLiteral());
      } catch (DuplicateBindingTypeException e)
      {
         duplicateDetected = true;
      }
      assert duplicateDetected;

      // Test annotations that are not binding types
      boolean nonBindingTypeDetected = false;
      try
      {
         eventComponent.fire(anEvent, new FishStereotypeAnnotationLiteral());
      } catch (IllegalArgumentException e)
      {
         nonBindingTypeDetected = true;
      }
      assert nonBindingTypeDetected;
   }