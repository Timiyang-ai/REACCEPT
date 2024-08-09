public void notify(Manager manager, T event)
   {
      // Get the most specialized instance of the component
      Object instance = getInstance(manager);
      if (instance != null)
         this.observerMethod.invoke(manager, instance, event);
   }