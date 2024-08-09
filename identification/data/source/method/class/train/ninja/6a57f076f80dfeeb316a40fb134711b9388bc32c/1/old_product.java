public Route buildRoute(Injector injector) {
        if(controller == null && result == null) {
            log.error("Error in route configuration for {}", uri);
            throw new IllegalStateException("Route not with a controller or result");
        }

        // Calculate filters
        LinkedList<Class<? extends Filter>> filters = new LinkedList<Class<? extends Filter>>();
        if(controller != null) {
            if (controllerMethod == null) {
                throw new IllegalStateException(
                        String.format("Route '%s' does not have a controller method", uri));
            }
            filters.addAll(calculateFiltersForClass(controller));
            FilterWith filterWith = controllerMethod
                    .getAnnotation(FilterWith.class);
            if (filterWith != null) {
                filters.addAll(Arrays.asList(filterWith.value()));
            }
        }

        return new Route(httpMethod, uri, controller, controllerMethod,
                buildFilterChain(injector, filters, controller,
                        controllerMethod, result));
    }