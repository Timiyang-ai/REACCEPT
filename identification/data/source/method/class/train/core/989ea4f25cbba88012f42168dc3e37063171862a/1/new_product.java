public boolean isObserverInterested(Annotation... bindings)
   {
      // Simply check that all event bindings specified by the observer are
      // in the list provided.
      List<Annotation> bindingsArray = Arrays.asList(bindings);
      boolean result = true;
      if (!this.eventBindings.isEmpty())
         result = bindingsArray.containsAll(this.eventBindings);
      return result;
   }