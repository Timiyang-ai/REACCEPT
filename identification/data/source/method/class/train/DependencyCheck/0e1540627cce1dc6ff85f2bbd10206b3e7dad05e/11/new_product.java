public synchronized Set<Identifier> getSoftwareIdentifiers() {
        return Collections.unmodifiableSet(new TreeSet<>(softwareIdentifiers));
    }