public synchronized Set<Identifier> getIdentifiers() {
        return Collections.unmodifiableSet(new HashSet<>(identifiers));
    }