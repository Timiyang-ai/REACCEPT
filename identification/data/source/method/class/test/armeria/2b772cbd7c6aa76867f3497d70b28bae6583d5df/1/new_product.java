public static Function<Service<HttpRequest, HttpResponse>, BraveService>
    newDecorator(Tracing tracing) {
        return newDecorator(HttpTracing.newBuilder(tracing)
                                       .serverParser(ArmeriaHttpServerParser.get())
                                       .build());
    }