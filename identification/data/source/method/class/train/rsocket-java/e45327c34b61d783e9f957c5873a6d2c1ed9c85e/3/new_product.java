public static void dispose(String name) {
    Objects.requireNonNull(name, "name must not be null");
    registry.remove(name);
  }