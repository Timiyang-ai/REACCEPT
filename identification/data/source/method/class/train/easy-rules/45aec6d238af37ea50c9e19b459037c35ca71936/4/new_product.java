public Object get(String name) {
        Objects.requireNonNull(name);
        return facts.get(name);
    }