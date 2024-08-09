public synchronized void add(final User user) {
    users.put(user.name(), user);
  }