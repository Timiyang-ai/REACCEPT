public ServerBuilder decorator(Function<? super HttpService, ? extends HttpService> decorator) {
        return decorator(Route.builder().catchAll().build(), decorator);
    }