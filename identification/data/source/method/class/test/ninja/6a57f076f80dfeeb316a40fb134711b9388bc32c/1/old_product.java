public Route buildRoute(Injector injector) {
        // Calculate filters
        List<Class<? extends Filter>> filters = new ArrayList<Class<? extends Filter>>();
        if (controllerMethod != null) {
            filters.addAll(calculateFiltersForClass(controller));
            FilterWith filterWith = controllerMethod.getAnnotation(FilterWith.class);
            if (filterWith != null) {
                filters.addAll(Arrays.asList(filterWith.value()));
            }
        }
        return new Route(injector, httpMethod, uri, controller, controllerMethod, filters);
    }