public void remove(String name) {
        Objects.requireNonNull(name);
        facts.remove(name);
    }