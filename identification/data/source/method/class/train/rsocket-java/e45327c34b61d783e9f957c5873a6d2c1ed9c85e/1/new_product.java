public static LocalClientTransport create(String name) {
    Objects.requireNonNull(name, "name must not be null");

    return new LocalClientTransport(name);
  }