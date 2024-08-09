public ServerBuilder decorator(
            Route route,
            DecoratingServiceFunction<HttpRequest, HttpResponse> decoratingServiceFunction) {
        requireNonNull(decoratingServiceFunction, "decoratingServiceFunction");
        return decorator(route,
                         delegate -> new FunctionalDecoratingService<>(delegate, decoratingServiceFunction));
    }