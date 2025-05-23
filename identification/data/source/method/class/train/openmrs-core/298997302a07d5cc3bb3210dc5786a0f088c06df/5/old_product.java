public static <H, T> List<H> getHandlersForType(Class<H> handlerType, Class<T> type) {
		
		List<H> handlers = new ArrayList<H>();
		
		// First get all registered components of the passed class
		for (H handler : Context.getRegisteredComponents(handlerType)) {
			Handler handlerAnnotation = handler.getClass().getAnnotation(Handler.class);
			// Only consider those that have been annotated as Handlers
			if (handlerAnnotation != null) {
				// If no type is passed in return all handlers
				if (type == null) {
					handlers.add(handler);
				}
				// Otherwise, return all handlers that support the passed type
				else {
					for (int i = 0; i < handlerAnnotation.supports().length; i++) {
						Class<?> clazz = handlerAnnotation.supports()[i];
						if (clazz.isAssignableFrom(type)) {
							handlers.add(handler);
						}
					}
				}
			}
		}
		
		// Return the list of handlers based on the order specified in the Handler annotation
		Collections.sort(handlers, new Comparator<H>() {
			
			public int compare(H o1, H o2) {
				return getOrderOfHandler(o1.getClass()).compareTo(getOrderOfHandler(o2.getClass()));
			}
		});
		
		return handlers;
	}