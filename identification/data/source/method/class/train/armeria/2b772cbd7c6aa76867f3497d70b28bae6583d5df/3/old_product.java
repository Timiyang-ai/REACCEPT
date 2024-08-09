public static Function<Client<HttpRequest, HttpResponse>, BraveClient> newDecorator(Tracing tracing) {
        return newDecorator(tracing, null);
    }