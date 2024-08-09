public boolean isObserverInterested(Annotation... bindings)
   {
      // Simply check that all event bindings specified by the observer are
      // in the list provided.
      if (this.eventBindings.isEmpty())
      {
         return true;
      }
      else
      {
	     //List<Annotation> bindingsArray = Arrays.asList(bindings);
         //return bindingsArray.containsAll(this.eventBindings);
         for (Annotation x: eventBindings) {
            boolean found = false;
            for (Annotation y: bindings)
            {
        	if ( metaDataCache.getBindingTypeModel(x.annotationType()).isEqual(x, y) ) {
               found = true;
            }
            }
            if (!found) return false;
         }
         return true;
      }
   }