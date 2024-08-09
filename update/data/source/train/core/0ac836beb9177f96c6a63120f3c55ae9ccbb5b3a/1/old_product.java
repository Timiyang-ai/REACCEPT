public void notify(Manager container, T event)
   {
      // Get the most specialized instance of the component
      Object instance = container.getInstanceByType(compModel.getType(),
            compModel.getBindingTypes().toArray(new Annotation[0]));
      if (instance != null)
         this.observerMethod.invoke(container, instance, event);
   }