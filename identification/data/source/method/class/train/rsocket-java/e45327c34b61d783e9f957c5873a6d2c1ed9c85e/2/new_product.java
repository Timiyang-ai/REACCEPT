static @Nullable ServerDuplexConnectionAcceptor findServer(String name) {
    Objects.requireNonNull(name, "name must not be null");

    return registry.get(name);
  }