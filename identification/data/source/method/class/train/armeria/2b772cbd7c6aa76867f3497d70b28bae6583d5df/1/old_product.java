public static Function<Client<HttpRequest, HttpResponse>, BraveClient> newDecorator(
            Tracing tracing, @Nullable String remoteServiceName) {
        try {
            ensureScopeUsesRequestContext(tracing);
        } catch (IllegalStateException e) {
            logger.warn("{} - it is appropriate to ignore this warning if this client is not being used " +
                        "inside an Armeria server (e.g., this is a normal spring-mvc tomcat server).",
                        e.getMessage());
        }
        return delegate -> new BraveClient(delegate, tracing, remoteServiceName);
    }