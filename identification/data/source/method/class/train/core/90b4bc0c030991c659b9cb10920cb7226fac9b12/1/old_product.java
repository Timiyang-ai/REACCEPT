public boolean isObserverInterested(Annotation... bindings)
   {
      // TODO This logic needs to be in injectable
      boolean result = true;
      // Check each binding specified by this observer against those provided
      if (this.eventBindings != null)
      {
         if ((bindings != null) && (bindings.length > 0))
         {
            List<Annotation> bindingsArray = Arrays.asList(bindings);
            for (Annotation annotation : this.eventBindings)
            {
               int eventBindingIndex = bindingsArray.indexOf(annotation);
               if (eventBindingIndex >= 0)
               {
                  // TODO Use annotation equality
                  result = annotationsMatch(annotation, bindingsArray.get(eventBindingIndex));
               } else
               {
                  result = false;
                  break;
               }
            }
         }
      }
      return result;
   }