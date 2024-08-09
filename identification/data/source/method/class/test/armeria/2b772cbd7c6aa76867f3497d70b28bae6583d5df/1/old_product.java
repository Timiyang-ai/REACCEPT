public static Function<Service<HttpRequest, HttpResponse>, BraveService>
    newDecorator(Tracing tracing) {
        ensureScopeUsesRequestContext(tracing);
        return service -> new BraveService(service, tracing);
    }