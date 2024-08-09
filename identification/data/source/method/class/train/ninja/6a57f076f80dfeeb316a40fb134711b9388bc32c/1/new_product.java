public Route buildRoute(Injector injector) {
        if (functionalMethod == null) {
            log.error("Error in route configuration for {}", uri);
            throw new IllegalStateException("Route missing a controller method");
        }

        // Calculate filters
        LinkedList<Class<? extends Filter>> filters = new LinkedList<>();
        filters.addAll(calculateFiltersForClass(functionalMethod.getDeclaringClass()));
        FilterWith filterWith = functionalMethod
                .getAnnotation(FilterWith.class);
        if (filterWith != null) {
            filters.addAll(Arrays.asList(filterWith.value()));
        }
        
        FilterChain filterChain = buildFilterChain(injector, filters);

        return new Route(httpMethod, uri, functionalMethod, filterChain);
    }