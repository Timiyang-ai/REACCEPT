public Route buildRoute(Injector injector) {
		// Calculate filters
		LinkedList<Class<? extends Filter>> filters = new LinkedList<Class<? extends Filter>>();
        filters.addAll(calculateFiltersForClass(controller));
        FilterWith filterWith = controllerMethod
                .getAnnotation(FilterWith.class);
        if (filterWith != null) {
            filters.addAll(Arrays.asList(filterWith.value()));
        }
		return new Route(httpMethod, uri, controller, controllerMethod,
				buildFilterChain(injector, filters, controller,
						controllerMethod));
	}