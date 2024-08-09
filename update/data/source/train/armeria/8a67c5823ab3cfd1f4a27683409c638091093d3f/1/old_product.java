public <T extends Client<I, O>, R extends Client<I, O>, I extends HttpRequest, O extends HttpResponse>
    B decorator(Function<T, R> decorator) {
        decoration.add(decorator);
        return self();
    }