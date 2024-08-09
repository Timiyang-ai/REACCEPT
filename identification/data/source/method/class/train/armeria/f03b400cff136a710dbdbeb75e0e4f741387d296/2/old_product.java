public ServerBuilder decorator(
            String pathPattern,
            DecoratingServiceFunction<HttpRequest, HttpResponse> decoratingServiceFunction) {
        return decorator(Route.builder().path(pathPattern).build(), decoratingServiceFunction);
    }