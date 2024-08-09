@SuppressWarnings("unchecked")
    public <T> T get(String name) {
        Objects.requireNonNull(name);
        return (T) facts.get(name);
    }