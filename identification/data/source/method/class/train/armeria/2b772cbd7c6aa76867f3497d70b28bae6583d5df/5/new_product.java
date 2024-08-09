public static Function<? super HttpClient, BraveClient> newDecorator(
            Tracing tracing, @Nullable String remoteServiceName) {
        HttpTracing httpTracing = HttpTracing.newBuilder(tracing)
                                             .clientParser(ArmeriaHttpClientParser.get())
                                             .build();
        if (remoteServiceName != null) {
            httpTracing = httpTracing.clientOf(remoteServiceName);
        }
        return newDecorator(httpTracing);
    }