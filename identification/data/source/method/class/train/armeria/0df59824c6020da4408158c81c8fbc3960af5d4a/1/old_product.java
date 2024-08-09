public ServerBuilder decorator(
            DecoratingServiceFunction<HttpRequest, HttpResponse> decoratingServiceFunction) {
        requireNonNull(decoratingServiceFunction, "decoratingServiceFunction");
        return decorator(delegate -> new FunctionalDecoratingService<>(delegate, decoratingServiceFunction));
    }