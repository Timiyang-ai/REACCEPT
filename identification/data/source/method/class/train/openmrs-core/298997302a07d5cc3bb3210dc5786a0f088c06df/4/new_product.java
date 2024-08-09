public static <H, T> List<H> getHandlersForType(Class<H> handlerType, Class<T> type) {
		List<?> list = cachedHandlers.get(new Key(handlerType, type));
		if (list != null) {
			return (List<H>) list;
		}
		
		List<H> handlers = new ArrayList<>();
		
		// First get all registered components of the passed class
		log.debug("Getting handlers of type " + handlerType + (type == null ? "" : " for class " + type.getName()));
		for (H handler : Context.getRegisteredComponents(handlerType)) {
			Handler handlerAnnotation = handler.getClass().getAnnotation(Handler.class);
			// Only consider those that have been annotated as Handlers
			if (handlerAnnotation != null) {
				// If no type is passed in return all handlers
				if (type == null) {
					log.debug("Found handler " + handler.getClass());
					handlers.add(handler);
				}
				// Otherwise, return all handlers that support the passed type
				else {
					for (int i = 0; i < handlerAnnotation.supports().length; i++) {
						Class<?> clazz = handlerAnnotation.supports()[i];
						if (clazz.isAssignableFrom(type)) {
							log.debug("Found handler: " + handler.getClass());
							handlers.add(handler);
						}
					}
				}
			}
		}
		
		// Return the list of handlers based on the order specified in the Handler annotation
		handlers.sort(new Comparator<H>() {

			@Override
			public int compare(H o1, H o2) {
				return getOrderOfHandler(o1.getClass()).compareTo(getOrderOfHandler(o2.getClass()));
			}
		});
		
		Map<Key, List<?>> newCachedHandlers = new WeakHashMap<>(cachedHandlers);
		newCachedHandlers.put(new Key(handlerType, type), handlers);
		cachedHandlers = newCachedHandlers;
		
		return handlers;
	}