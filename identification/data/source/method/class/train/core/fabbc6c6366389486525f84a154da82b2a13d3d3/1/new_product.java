public void observe(Observer<T> observer, Annotation... bindingTypes)
   {
      manager.addObserver(observer, type, mergeBindings(bindingTypes));
   }