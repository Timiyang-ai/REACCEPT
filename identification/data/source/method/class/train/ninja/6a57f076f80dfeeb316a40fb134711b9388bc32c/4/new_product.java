public Route buildRoute(Injector injector) {
        if (functionalMethod == null) {
            log.error("Error in route configuration for {}", uri);
            throw new IllegalStateException("Route missing a controller method");
        }

        // Calculate filters
        LinkedList<Class<? extends Filter>> allFilters = new LinkedList<>();
        
        allFilters.addAll(calculateGlobalFilters(this.globalFiltersOptional, injector));

        allFilters.addAll(this.localFilters);
        
        allFilters.addAll(calculateFiltersForClass(functionalMethod.getDeclaringClass()));
        FilterWith filterWith = functionalMethod.getAnnotation(FilterWith.class);
        if (filterWith != null) {
            allFilters.addAll(Arrays.asList(filterWith.value()));
        }
        
        FilterChain filterChain = buildFilterChain(injector, allFilters);

        return new Route(httpMethod, uri, functionalMethod, filterChain);
    }