static ServerDuplexConnectionAcceptor findServer(String name) {
    return registry.get(name);
  }