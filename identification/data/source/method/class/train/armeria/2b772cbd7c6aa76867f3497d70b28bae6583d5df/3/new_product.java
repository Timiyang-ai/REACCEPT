public static Function<? super HttpClient, BraveClient> newDecorator(Tracing tracing) {
        return newDecorator(tracing, null);
    }