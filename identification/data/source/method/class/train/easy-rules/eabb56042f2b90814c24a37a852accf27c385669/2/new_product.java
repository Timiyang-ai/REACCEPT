public Object remove(String name) {
        Objects.requireNonNull(name);
        return facts.remove(name);
    }