public static Function<? super HttpService, BraveService>
    newDecorator(Tracing tracing) {
        return newDecorator(HttpTracing.newBuilder(tracing)
                                       .serverParser(ArmeriaHttpServerParser.get())
                                       .build());
    }