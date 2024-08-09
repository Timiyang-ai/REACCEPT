public boolean isObserverInterested(Annotation... bindings)
   {
      // TODO This logic needs to be in injectable
      boolean result = true;
      // Check each binding specified by this observer against those provided
      if (this.eventBindings.length > 0)
      {
         if ((bindings != null) && (bindings.length > 0))
         {
            List<Annotation> bindingsArray = Arrays.asList(bindings);
            for (Annotation annotation : this.eventBindings)
            {
               int eventBindingIndex = bindingsArray.indexOf(annotation);
               if (eventBindingIndex >= 0)
               {
                  //result = annotationsMatch(annotation, bindingsArray.get(eventBindingIndex));
                  result = annotation.equals(bindingsArray.get(eventBindingIndex));
               } else
               {
                  result = false;
                  break;
               }
            }
         } else
         {
            result = false;
         }
      }
      return result;
   }