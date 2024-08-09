public ServerBuilder decorator(
            DecoratingServiceFunction<HttpRequest, HttpResponse> decoratingServiceFunction) {
        return decorator(Route.builder().catchAll().build(), decoratingServiceFunction);
    }