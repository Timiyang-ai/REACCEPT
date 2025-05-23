public void observe(Observer<T> observer, Annotation... bindingTypes)
   {
      Set<Annotation> bindingParameters = checkBindingTypes(bindingTypes);
      bindingParameters.addAll(this.bindingTypes);
      ManagerImpl.instance().addObserver(observer, eventType, bindingParameters.toArray(new Annotation[0]));
   }