public B decorator(Function<? super HttpClient, ? extends HttpClient> decorator) {
        decoration.add(decorator);
        return self();
    }